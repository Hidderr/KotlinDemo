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
    private static final String TAG = "ChineseOutPutHtmlTask";
    private static final String HTML_TITLE_CONTENT = "APK大小检测报告";

    public ChineseOutPutHtmlTask(int type, JsonObject config) throws ParserConfigurationException {
        super(type, config);
    }

    @Override
    public void format(JsonObject jsonObject) throws ParserConfigurationException {
//        if(true){
//            addHtmlTitle();
//            return;
//        }

        MMTaskJsonResult.formatJson(jsonObject, (JsonObject)null, this.config);
        int taskType = jsonObject.get("taskType").getAsInt();
        System.out.println("............   format taskType: "+taskType+this.document.getDocumentElement());
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
            span.setAttribute("style", "color:white;font-size:20px;background-color:#C0C0C0");
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

    private void addHtmlTitle() {
        System.out.println(TAG + "addHtmlTitle: startCreateTitle");
        Element title = this.document.createElement("div");
        System.out.println(TAG + "addHtmlTitle: hascreate");
        title.setTextContent(HTML_TITLE_CONTENT);
        title.setAttribute("width", "100%");
        title.setAttribute("style", "text-align: center");
        System.out.println(TAG + "addHtmlTitle: addAttribute");
        this.document.appendChild(title);
        System.out.println(TAG + "addHtmlTitle: addChild");
    }

    private Element formatUnzipTask(JsonObject jsonObject) {
        addHtmlTitle();
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
                Element trS1 = this.document.createElement("tr");
                Element td1S1 = this.document.createElement("td");
                td1S1.setTextContent(suffix);
                Element td2S1 = this.document.createElement("td");
                td2S1.setTextContent(Util.formatByteUnit(size));
                trS1.appendChild(td1S1);
                trS1.appendChild(td2S1);
                table.appendChild(trS1);
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
                Element trS2 = this.document.createElement("tr");
                Element td1S2 = this.document.createElement("td");
                td1S2.setTextContent((String)entry.getKey());
                Element td2S2 = this.document.createElement("td");
                td2S2.setTextContent(((JsonElement)entry.getValue()).getAsString());
                trS2.appendChild(td1S2);
                trS2.appendChild(td2S2);
                table.appendChild(trS2);
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
                    Element trS3 = this.document.createElement("tr");
                    Element td1S3 = this.document.createElement("td");
                    td1S3.setTextContent(filename);
                    Element td2S3 = this.document.createElement("td");
                    td2S3.setTextContent(Util.formatByteUnit(((JsonObject)file).get("entry-size").getAsLong()));
                    trS3.appendChild(td1S3);
                    trS3.appendChild(td2S3);
                    table.appendChild(trS3);
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
            Element tr = this.document.createElement("tr");
            Element td = this.document.createElement("td");
            td.setTextContent("taskDescription");
            Element td1 = this.document.createElement("td");
            td1.setTextContent(jsonObject.get("taskDescription").getAsString());
            tr.appendChild(td);
            tr.appendChild(td1);
            table.appendChild(tr);
            JsonArray groups = jsonObject.getAsJsonArray("groups");
            Iterator var11 = groups.iterator();

            while(var11.hasNext()) {
                JsonElement entry = (JsonElement)var11.next();
                JsonObject object = (JsonObject)entry;
                Element trS4 = this.document.createElement("tr");
                Element td1S4 = this.document.createElement("td");
                td1S4.setTextContent(object.get("name").getAsString());
                Element td2S4 = this.document.createElement("td");
                td2S4.setTextContent(object.get("method-count").getAsString());
                trS4.appendChild(td1S4);
                trS4.appendChild(td2S4);
                table.appendChild(trS4);
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
                    Element trS5 = this.document.createElement("tr");
                    Element td1S5 = this.document.createElement("td");
                    td1S5.setTextContent(filename);
                    Element td2S5 = this.document.createElement("td");
                    totalSize += ((JsonObject)file).get("entry-size").getAsLong();
                    td2S5.setTextContent(Util.formatByteUnit(((JsonObject)file).get("entry-size").getAsLong()));
                    trS5.appendChild(td1S5);
                    trS5.appendChild(td2S5);
                    table.appendChild(trS5);
                }
            }

            Element tr2 = this.document.createElement("tr");
            Element td11 = this.document.createElement("td");
            td11.setTextContent("total-size");
            Element td22 = this.document.createElement("td");
            td22.setTextContent(Util.formatByteUnit(totalSize));
            tr2.appendChild(td11);
            tr2.appendChild(td22);
            table.appendChild(tr2);
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
                Element trS6 = this.document.createElement("tr");
                Element td1S6 = this.document.createElement("td");
                td1S6.setTextContent(jsonObj.get("suffix").getAsString());
                Element td2S6 = this.document.createElement("td");
                td2S6.setTextContent(Util.formatByteUnit(jsonObj.get("total-size").getAsLong()));
                trS6.appendChild(td1S6);
                trS6.appendChild(td2S6);
                table.appendChild(trS6);
            }

            return table;
        }
    }


}
