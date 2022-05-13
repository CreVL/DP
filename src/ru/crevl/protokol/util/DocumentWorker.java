package ru.crevl.protokol.util;

import org.apache.poi.wp.usermodel.Paragraph;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DocumentWorker {
    public static void replaceMap(XWPFDocument document, Map<String, String> replacements) {
        for (var p: document.getParagraphs())
            replaceMapInternal(p, replacements);
        for (var t: document.getTables())
            replaceMapInternal(t, replacements);
    }

    private static void replaceMapInternal(XWPFParagraph paragraph, Map<String, String> replacements) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        XWPFRun prevRun = null;
        List<String> nullKeys = new ArrayList<>();
        for (var r: paragraph.getRuns())
        {
            var text = r.text();
            if (text == null) continue;
            if (flag) {
                if (r.text().contains("$"))
                {
                    sb.append(text, 0, text.indexOf('$'));
                    flag = false;
                    if (!replacements.containsKey(sb.toString())) nullKeys.add(sb.toString());
                    String rpl = replacements.getOrDefault(sb.toString(), "");

                    prevRun.setText(rpl, 0);
                    sb.setLength(0);
                    r.setText(r.text().substring(text.indexOf('$') + 1), 0);
                }
                else {
                    sb.append(r.text());
                    r.setText("", 0);
                }
            }
            else if (r.text().endsWith("$$"))
            {
                r.setText(r.text().substring(0, r.text().length() - 2), 0);
                flag = true;
            }
            else {
                char prev = ' ';
                for (char c: text.toCharArray()) {
                    if (c == '$') {
                        if (flag) {
                            flag = false;
                            String rpl = replacements.getOrDefault(sb.toString(), "");
                            r.setText(r.text().replace("$$" + sb.toString() + "$",
                                    rpl), 0);
                            if (!replacements.containsKey(sb.toString())) nullKeys.add(sb.toString());
                            sb.setLength(0);
                        }
                        else if (prev == '$') {
                            flag = true;
                        }
                    }
                    else if (flag) sb.append(c);
                    prev = c;
                }
            }
            prevRun = r;
        }
        for (String key : nullKeys)
            System.out.println(key);
    }

    private static void replaceMapInternal(XWPFTable table, Map<String, String> replacements) {

        for (var r: table.getRows())
            for (var c: r.getTableCells()) {
                for (var t: c.getTables())
                    replaceMapInternal(t, replacements);
                for (var p: c.getParagraphs())
                    replaceMapInternal(p, replacements);
            }
    }
}
