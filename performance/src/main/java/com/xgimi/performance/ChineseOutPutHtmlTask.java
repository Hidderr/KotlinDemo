package com.xgimi.performance;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tencent.matrix.apk.model.output.MMTaskJsonResult;
import com.tencent.matrix.apk.model.result.TaskHtmlResult;
import com.tencent.matrix.javalib.util.Util;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: tongpin.li
 * @Maintainer: alan.li@xgimi.com
 * @Date: 2019/9/19
 * @Copyright: 2019 www.andriodtvdev.com Inc. All rights reserved.
 * @description:
 */
public class ChineseOutPutHtmlTask extends TaskHtmlResult {

    private static final int MAX_SHOW_ITEMS = 10;
    private static final String FOLDER_STYLE = "color:white;font-size:20px;background-color:#C0C0C0";
    private static final String FOLDER_ONCLICK = "root = this.parentNode.parentNode; next = root.nextSibling; while (next != null) { if (next.hasAttribute('hidden')) { next.removeAttribute('hidden');} else { break; } next = next.nextSibling; } root.parentNode.removeChild(root)";

    public ChineseOutPutHtmlTask(int type, JsonObject config) throws ParserConfigurationException {
        super(type, config);
    }

    @Override
    public void format(JsonObject jsonObject) throws ParserConfigurationException {
        MMTaskJsonResult.formatJson(jsonObject, (JsonObject)null, this.config);
        int taskType = jsonObject.get("taskType").getAsInt();
        Element element;
        switch(taskType) {
            case 1:
                element = this.formatUnzipTask(jsonObject);
                this.foldElement(element);
                this.document.appendChild(element);
                break;
            case 2:
                element = this.formatManifestAnalyzeTask(jsonObject);
                this.foldElement(element);
                this.document.appendChild(element);
                break;
            case 3:
                element = this.formatShowFileSizeTask(jsonObject);
                this.foldElement(element);
                this.document.appendChild(element);
                break;
            case 4:
                element = this.formatMethodCountTask(jsonObject);
                this.foldElement(element);
                this.document.appendChild(element);
                break;
            case 5:
            case 7:
            default:
                jsonObject.remove("taskType");
                jsonObject.remove("start-time");
                jsonObject.remove("end-time");
                super.format(jsonObject);
                this.foldElement((Element)this.document.getFirstChild());
                break;
            case 6:
                element = this.formatFindNonAlphaPngTask(jsonObject);
                this.foldElement(element);
                this.document.appendChild(element);
                break;
            case 8:
                element = this.formatUncompressedFileTask(jsonObject);
                this.foldElement(element);
                this.document.appendChild(element);
        }

    }

    private void foldElement(Element element) {
        int i;
        if (element != null && element.getChildNodes().getLength() > 10) {
            for(i = 10; i < element.getChildNodes().getLength(); ++i) {
                if (element.getChildNodes().item(i) instanceof Element) {
                    ((Element)element.getChildNodes().item(i)).setAttribute("hidden", "true");
                }
            }

            Element span = this.document.createElement("span");
            span.setAttribute("style", "color:yellow;font-size:20px;background-color:#C0C0C0");
            span.setAttribute("onClick", "root = this.parentNode.parentNode; next = root.nextSibling; while (next != null) { if (next.hasAttribute('hidden')) { next.removeAttribute('hidden');} else { break; } next = next.nextSibling; } root.parentNode.removeChild(root)");
            span.setTextContent("...");
            Element folder = null;
            Element parent;
            if (element.getTagName().equals("table")) {
                folder = this.document.createElement("tr");
                parent = this.document.createElement("td");
                parent.setAttribute("colspan", "100%");
                folder.appendChild(parent);
                parent.appendChild(span);
            } else if (element.getTagName().equals("ul")) {
                folder = this.document.createElement("li");
                parent = this.document.createElement("span");
                parent.appendChild(span);
                folder.appendChild(parent);
            }

            if (folder != null) {
                element.insertBefore(folder, element.getChildNodes().item(10));
            }
        }

        for(i = 0; i < element.getChildNodes().getLength(); ++i) {
            if (element.getChildNodes().item(i) instanceof Element) {
                this.foldElement((Element)element.getChildNodes().item(i));
            }
        }

    }

    private Element formatUnzipTask(JsonObject jsonObject) {
        Element table = this.document.createElement("table");
        table.setAttribute("border", "1");
        table.setAttribute("width", "100%");
        if (jsonObject == null) {
            return table;
        } else {
            Element tr = this.document.createElement("tr");
            Element td1 = this.document.createElement("td");
            td1.setTextContent("taskDescription");
            Element td2 = this.document.createElement("td");
            td2.setTextContent(jsonObject.get("taskDescription").getAsString());
            tr.appendChild(td1);
            tr.appendChild(td2);
            table.appendChild(tr);
            tr = this.document.createElement("tr");
            td1 = this.document.createElement("td");
            td1.setTextContent("total-size");
            td2 = this.document.createElement("td");
            td2.setTextContent(Util.formatByteUnit(jsonObject.get("total-size").getAsLong()));
            tr.appendChild(td1);
            tr.appendChild(td2);
            table.appendChild(tr);
            JsonArray files = jsonObject.getAsJsonArray("entries");
            Iterator var13 = files.iterator();

            while(var13.hasNext()) {
                JsonElement file = (JsonElement)var13.next();
                String suffix = ((JsonObject)file).get("suffix").getAsString();
                long size = ((JsonObject)file).get("total-size").getAsLong();
                Element tr0 = this.document.createElement("tr");
                Element td11 = this.document.createElement("td");
                td1.setTextContent(suffix);
                Element td22 = this.document.createElement("td");
                td2.setTextContent(Util.formatByteUnit(size));
                tr0.appendChild(td11);
                tr0.appendChild(td22);
                table.appendChild(tr0);
            }

            return table;
        }
    }

    private Element formatManifestAnalyzeTask(JsonObject jsonObject) {
        Element table = this.document.createElement("table");
        table.setAttribute("border", "1");
        table.setAttribute("width", "100%");
        if (jsonObject == null) {
            return table;
        } else {
            Element tr = this.document.createElement("tr");
            Element td1 = this.document.createElement("td");
            td1.setTextContent("taskDescription");
            Element td2 = this.document.createElement("td");
            td2.setTextContent(jsonObject.get("taskDescription").getAsString());
            tr.appendChild(td1);
            tr.appendChild(td2);
            table.appendChild(tr);
            JsonObject manifest = jsonObject.getAsJsonObject("manifest");
            Iterator var10 = manifest.entrySet().iterator();

            while(var10.hasNext()) {
                Map.Entry<String, JsonElement> entry = (Map.Entry)var10.next();
                Element tr3 = this.document.createElement("tr");
                Element td14 = this.document.createElement("td");
                td1.setTextContent((String)entry.getKey());
                Element td24 = this.document.createElement("td");
                td2.setTextContent(((JsonElement)entry.getValue()).getAsString());
                tr3.appendChild(td14);
                tr3.appendChild(td24);
                table.appendChild(tr3);
            }

            return table;
        }
    }

    private Element formatShowFileSizeTask(JsonObject jsonObject) {
        Element table = this.document.createElement("table");
        table.setAttribute("border", "1");
        table.setAttribute("width", "100%");
        if (jsonObject == null) {
            return table;
        } else {
            Element tr = this.document.createElement("tr");
            Element td1 = this.document.createElement("td");
            td1.setTextContent("taskDescription");
            Element td2 = this.document.createElement("td");
            td2.setTextContent(jsonObject.get("taskDescription").getAsString());
            tr.appendChild(td1);
            tr.appendChild(td2);
            table.appendChild(tr);
            JsonArray files = jsonObject.getAsJsonArray("files");
            Iterator var11 = files.iterator();

            while(var11.hasNext()) {
                JsonElement file = (JsonElement)var11.next();
                String filename = ((JsonObject)file).get("entry-name").getAsString();
                if (!Util.isNullOrNil(filename)) {
                    Element tr5 = this.document.createElement("tr");
                    Element td15 = this.document.createElement("td");
                    td1.setTextContent(filename);
                    Element td25 = this.document.createElement("td");
                    td2.setTextContent(Util.formatByteUnit(((JsonObject)file).get("entry-size").getAsLong()));
                    tr5.appendChild(td15);
                    tr5.appendChild(td25);
                    table.appendChild(tr5);
                }
            }

            return table;
        }
    }

    private Element formatMethodCountTask(JsonObject jsonObject) {
        Element table = this.document.createElement("table");
        table.setAttribute("border", "1");
        table.setAttribute("width", "100%");
        if (jsonObject == null) {
            return table;
        } else {
            Element t = this.document.createElement("tr");
            Element tr = this.document.createElement("td");
            tr.setTextContent("taskDescription");
            Element td1 = this.document.createElement("td");
            td1.setTextContent(jsonObject.get("taskDescription").getAsString());
            t.appendChild(tr);
            t.appendChild(td1);
            table.appendChild(t);
            JsonArray groups = jsonObject.getAsJsonArray("groups");
            Iterator var11 = groups.iterator();

            while(var11.hasNext()) {
                JsonElement entry = (JsonElement)var11.next();
                JsonObject object = (JsonObject)entry;
                Element tr6 = this.document.createElement("tr");
                Element td16 = this.document.createElement("td");
                td1.setTextContent(object.get("name").getAsString());
                Element td2 = this.document.createElement("td");
                td2.setTextContent(object.get("method-count").getAsString());
                tr6.appendChild(td16);
                tr6.appendChild(td2);
                table.appendChild(tr6);
            }

            tr = this.document.createElement("tr");
            td1 = this.document.createElement("td");
            td1.setTextContent("total-methods");
            Element td2 = this.document.createElement("td");
            td2.setTextContent(jsonObject.get("total-methods").getAsString() + " methods");
            tr.appendChild(td1);
            tr.appendChild(td2);
            table.appendChild(tr);
            return table;
        }
    }

    private Element formatFindNonAlphaPngTask(JsonObject jsonObject) {
        Element table = this.document.createElement("table");
        table.setAttribute("border", "1");
        table.setAttribute("width", "100%");
        if (jsonObject == null) {
            return table;
        } else {
            Element tr = this.document.createElement("tr");
            Element td1 = this.document.createElement("td");
            td1.setTextContent("taskDescription");
            Element td2 = this.document.createElement("td");
            td2.setTextContent(jsonObject.get("taskDescription").getAsString());
            tr.appendChild(td1);
            tr.appendChild(td2);
            table.appendChild(tr);
            long totalSize = 0L;
            JsonArray files = jsonObject.getAsJsonArray("files");
            Iterator var6 = files.iterator();

            while(var6.hasNext()) {
                JsonElement file = (JsonElement)var6.next();
                String filename = ((JsonObject)file).get("entry-name").getAsString();
                if (!Util.isNullOrNil(filename)) {
                    Element tr7 = this.document.createElement("tr");
                    Element td17 = this.document.createElement("td");
                    td1.setTextContent(filename);
                    Element td27 = this.document.createElement("td");
                    totalSize += ((JsonObject)file).get("entry-size").getAsLong();
                    td2.setTextContent(Util.formatByteUnit(((JsonObject)file).get("entry-size").getAsLong()));
                    tr7.appendChild(td17);
                    tr7.appendChild(td27);
                    table.appendChild(tr7);
                }
            }

            Element tr8 = this.document.createElement("tr");
            Element td18 = this.document.createElement("td");
            td1.setTextContent("total-size");
            Element td28 = this.document.createElement("td");
            td2.setTextContent(Util.formatByteUnit(totalSize));
            tr8.appendChild(td18);
            tr8.appendChild(td28);
            table.appendChild(tr8);
            return table;
        }
    }

    private Element formatUncompressedFileTask(JsonObject jsonObject) {
        Element table = this.document.createElement("table");
        table.setAttribute("border", "1");
        table.setAttribute("width", "100%");
        if (jsonObject == null) {
            return table;
        } else {
            Element tr = this.document.createElement("tr");
            Element td1 = this.document.createElement("td");
            td1.setTextContent("taskDescription");
            Element td2 = this.document.createElement("td");
            td2.setTextContent(jsonObject.get("taskDescription").getAsString());
            tr.appendChild(td1);
            tr.appendChild(td2);
            table.appendChild(tr);
            JsonArray entries = jsonObject.getAsJsonArray("files");
            Iterator var11 = entries.iterator();

            while(var11.hasNext()) {
                JsonElement jsonElement = (JsonElement)var11.next();
                JsonObject jsonObj = jsonElement.getAsJsonObject();
                Element tr9 = this.document.createElement("tr");
                Element td19 = this.document.createElement("td");
                td1.setTextContent(jsonObj.get("suffix").getAsString());
                Element td29 = this.document.createElement("td");
                td2.setTextContent(Util.formatByteUnit(jsonObj.get("total-size").getAsLong()));
                tr9.appendChild(td19);
                tr9.appendChild(td29);
                table.appendChild(tr9);
            }

            return table;
        }
    }
}
