package test.ngocpt.auth.dto.response;


import lombok.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@With
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MailResponse {
    private String recipient;
    private String subject;
    private String content;

    @SneakyThrows
    public MailResponse withContent(HashMap<String,String> body, String template){
        String content = Files.readString(Paths.get(getClass().getClassLoader().getResource("templates/"+template).toURI()));
        for (Map.Entry<String, String> e : body.entrySet()){
            content = content.replaceAll(Pattern.quote("${"+e.getKey()+"}"),e.getValue());
        }
        this.content =content;
        return this;
    }
}
