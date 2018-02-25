package jp.dip.oyasirazu.xmlviewer;

import java.util.Arrays;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

class Main {
    public static void main(String[] args) throws
            CmdLineException,
            XmlViewerException,
            XmlViewBuildException {
        // オプションオブジェクト準備
        CmdOptions options = new CmdOptions();

        // パーサー準備
        CmdLineParser optionParser = new CmdLineParser(options);

        // パース
        optionParser.parseArgument(args);

        XmlViewer xmlViewer = new XmlViewer(options.getTargetFilePaths().toArray(new String[0]));
        xmlViewer.buildView().show();
    }

    /**
     * コマンドラインオプションを表現するクラス。
     */
    static class CmdOptions {
        /**
         * 入力 XML ファイル。
         */
        @Argument(required = true, metaVar = "XML_FILE", usage = "Xml file path.")
        private List<String> targetFilePaths;

        public List<String> getTargetFilePaths() {
            return targetFilePaths;
        }
    }
}

