package util;

import com.chervonnaya.library.dto.BaseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T extends BaseDTO> String dtoToString(T DTO) {
        try {
            return mapper.writeValueAsString(DTO);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + DTO + "'", e);
        }
    }

}
