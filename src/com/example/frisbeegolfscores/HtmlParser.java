package com.example.frisbeegolfscores;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {
	
    List<HtmlData> listHtmlData = new ArrayList<HtmlData>();

	public List<HtmlData> parseHtml(String data) {
		String luokitus = "";
		String linkHref = "";
		String linkText = "";
		String karttaHref = "";
		String karttaText = "";

		Document doc = Jsoup.parse(data, "UTF-8");
		Elements tables = doc.select("table");
		if (tables.size() > 0) {
			for (Element table : tables) {
				Elements rows = table.select("tr");
				if (rows.size() > 0) {
					for (Element row : rows) {
						luokitus = "";
						linkHref = "";
						linkText = "";
						karttaHref = "";
						karttaText = "";
						Elements cols = row.select("td");
						Elements kuvaLuokitus = cols.get(1).select("img");
						if (kuvaLuokitus.size() > 0) {
							luokitus = kuvaLuokitus.attr("alt");
						}

						Elements linkRata = cols.get(2).select("a");
						if (linkRata.size() > 0) {
							linkHref = linkRata.attr("href");
							linkText = linkRata.text();
						}

						Elements linkKartta = cols.get(5).select("a");
						if (linkKartta.size() > 0) {
							karttaHref = linkKartta.attr("href");
							karttaText = linkKartta.text();
						}

						if (cols.get(0).text() != "#") {
							HtmlData htmldata = new HtmlData();
							htmldata.setId(cols.get(0).text());
							htmldata.setLuokitus(luokitus);
							htmldata.setRata(linkText);
							htmldata.setLink(linkHref);
							htmldata.setKaupunki(cols.get(3).text());
							htmldata.setVaylia(cols.get(4).text());
							htmldata.setKartta(karttaHref);

							listHtmlData.add(htmldata);
						}
					}
				}
			}
		}
		return listHtmlData;
	}
	
	/*
<table cellspacing="0" cellpadding="0" id="radat-table">
<tr class="table-odd"><td>#</td><td>&nbsp;</td><td>Rata</td><td>Paikkakunta</td><td>Väyliä</td><td> </td></tr>
<tr>
<td>1</td>
<td><img src="/files/rakennekuvat/c2.png" width="21" height="15" alt="C2" /></td>
<td><a href="rata/ahtela_sauvo">Ahtela, Sauvo</a></td>
<td>Sauvo</td>
<td>9</td>
<td> </td>
</tr>
*/
}
