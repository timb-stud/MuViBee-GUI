package muvibee.ean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

public class EANDates {

	static String preEAN = "http://www.bol.de/shop/home/suche/?fi=&st=&sa=&sr=&sv=&svb=&ssw=&si=&sk=&sd=&sre=&sq=";
	static String postEAN = "&forward=weiter&sswg=ANY#pm_features";
	static HtmlCleaner cleaner = new HtmlCleaner();

	private static Media getData(TagNode node, Media media) throws IOException {
		String title;
		String genre = "";
		String description;
		int yearOfRelease;

		TagNode[] titleNode = node.getElementsByAttValue("class", "pm_titel",
				true, true);
		title = titleNode[0].getText().toString();
		TagNode[] descriptionNode = node.getElementsByAttValue("class",
				"inner", true, true);
		StringBuffer sb = new StringBuffer();
		int j = 0;
		for (int i = 0; i < descriptionNode.length; i++) {
			if ((descriptionNode[i].getChildTags()[0].getOriginalSource())
					.contains("hd hd_content_box")) {
				if (descriptionNode[i].getChildTags()[0].getChildTags()[0]
						.getOriginalSource().contains("kurzbeschreibung")) {
					j = i;
					break;
				}
			}

		}
		sb.append(descriptionNode[j].getChildTags()[1].getText().toString()
				.trim());
		description = sb.toString();
		TagNode[] eanNode = node.getElementsByAttValue("class", "value pm_ean",
				true, true);
		String ean = eanNode[0].getText().toString();
		TagNode[] yearOfReleaseNode = node.getElementsByAttValue("class",
				"value pm_veroeffentlichungsdatum", true, true);
		if (yearOfReleaseNode[0].getText().toString().contains(".")) {
			String releaseYear = yearOfReleaseNode[0].getText().toString();
			releaseYear = releaseYear.replaceAll("[.]", "");
			releaseYear = releaseYear.substring(4, releaseYear.length());
			yearOfRelease = Integer.parseInt(releaseYear);
		} else {
			yearOfRelease = Integer.parseInt(yearOfReleaseNode[0].getText()
					.toString().replaceAll("[a-zA-Z]", "").trim());
		}
		TagNode[] genreNode = node.getElementsByAttValue("class",
				"value pm_stilrichtung", true, true);
		if (genreNode.length != 0)
			genre = genreNode[0].getText().toString();

		media.setTitle(title);
		media.setReleaseYear(yearOfRelease);
		media.setGenre(genre);
		media.setDescription(description);
		media.setEan(ean);

		loadImage(ean);

		return media;
	}

	private static void loadImage(String ean) throws IOException {
		URL url = new URL(preEAN + ean + postEAN);
		TagNode node = cleaner.clean(url);
		TagNode[] previewImage = node.getElementsByAttValue("id",
				"previewImage", true, true);

		String coverSource = (previewImage[0].getOriginalSource());
		coverSource = coverSource.substring(coverSource.indexOf("http:"));
		coverSource = coverSource.substring(0, coverSource.indexOf(".jpg") + 4);

		URL url_cover = new URL(coverSource);
		InputStream inputStr = url_cover.openStream();
		FileOutputStream fos = new FileOutputStream(
				"C:/Dokumente und Einstellungen/volkan g�kkaya/Desktop/test.jpeg");
		int temp = -1;
		while ((temp = inputStr.read()) != -1) {
			fos.write(temp);
		}
	}

	public Book getBookData(String ean) throws IOException {
		Book book = new Book();
		URL url = new URL(preEAN + ean + postEAN);
		TagNode node = cleaner.clean(url);
		book = (Book) getData(node, book);
		TagNode[] authorNode = node.getElementsByAttValue("class",
				"b9_author arrow", true, true);
		String author = authorNode[0].getText().toString();
		TagNode[] languageNode = node.getElementsByAttValue("class",
				"value pm_produktsprache", true, true);
		String language = languageNode[0].getText().toString();
		TagNode[] isbnNode = node.getElementsByAttValue("class",
				"value pm_isbn", true, true);
		String isbn = isbnNode[0].getText().toString();

		book.setAuthor(author);
		book.setLanguage(language);
		book.setIsbn(isbn);
		return book;
	}

	public Music getMusicData(String ean) throws IOException {
		Music music = new Music();
		URL url = new URL(preEAN + ean + postEAN);
		TagNode node = cleaner.clean(url);
		music = (Music) getData(node, music);

		TagNode[] interpreterNode = node.getElementsByAttValue("class",
				"bd bd_artikeldetails clearfix", true, true);
		TagNode[] typeNode = node.getElementsByAttValue("class",
				"pm_mediumbezeichnung", true, true);

		String type = typeNode[0].getText().toString();
		String interpreter = interpreterFix(interpreterNode);

		music.setType(type);
		music.setInterpreter(interpreter);
		music.setEan(ean);
		return music;
	}

	public Video getVideoData(String ean) throws IOException {
		Video video = new Video();
		URL url = new URL(preEAN + ean + postEAN);
		TagNode node = cleaner.clean(url);
		video = (Video) getData(node, video);

		TagNode[] directorNode = node.getElementsByAttValue("class",
				"b9_author arrow", true, true);
		String director = directorNode[0].getText().toString();
		TagNode[] actorsNode = node.getElementsByAttValue("class",
				"value pm_actors pm_readableName", true, true);
		String actors = "";
		for (int i = 0; i < actorsNode.length; i++) {
			actors = actors + actorsNode[i].getText().toString() + ", ";
		}
		video.setDirector(director);
		video.setActors(actors);
		video.setEan(ean);
		return video;
	}

	private String interpreterFix(TagNode[] interpreterNode) {
		String s = interpreterNode[0].getText().toString();
		s = s.substring(s.indexOf("Interpret"));
		if (s.contains("bol.de")) {
			s = s.substring(s.indexOf("Interpret"), s.indexOf("bol.de"));
		} else {
			s = s.substring(s.indexOf("Interpret"), s.indexOf("EAN"));
		}
		s = s.trim().replaceAll(" ", "");
		s = s.substring(s.indexOf("\n"));
		s = s.replaceAll("\n", "");
		return s;
	}

	private static void setProxy(String proxy, String port) {
		System.getProperties().put("proxySet", "true");
		System.getProperties().put("proxyHost", proxy);
		System.getProperties().put("proxyPort", port);
	}

	public static void main(String[] args) throws IOException {
		EANDates eanDates = new EANDates();
		setProxy("www-proxy.htw-saarland.de", "3128");
		// System.out.println(eanDates.getBookData("9783446235922"));
		// System.out.println(eanDates.getMusicData("0602527394527"));
		// System.out.println(eanDates.getVideoData("5050582778090"));
	}
}
