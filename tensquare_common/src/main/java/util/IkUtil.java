package util;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;

public class IkUtil {


    /**
     * 对制定文本进行中文分词
     *
     * @param content   源文本
     * @param splitChar 分词后结果的间隔符
     * @return 分词后的文本
     */
    public static String split(String content, String splitChar) throws IOException {
        StringReader reader = new StringReader(content);
        IKSegmenter ikSegmenter = new IKSegmenter(reader, true);
        Lexeme lexeme;
        StringBuilder result = new StringBuilder();
        while ((lexeme = ikSegmenter.next()) != null) {
            result.append(lexeme.getLexemeText()).append(splitChar);
        }
        return result.toString();
    }

}
