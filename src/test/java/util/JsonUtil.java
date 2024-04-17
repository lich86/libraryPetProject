package util;

import com.chervonnaya.library.dto.BaseDTO;
import com.chervonnaya.library.model.BaseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Set;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
    }

    public static <T extends BaseDTO> String dtoToString(T DTO) {
        try {
            return mapper.writeValueAsString(DTO);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + DTO + "'", e);
        }
    }

    public static <T extends BaseDTO> T stringToDto(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }

    public static <T extends BaseEntity> Set<T> stringToListOfEntities(String json, Class<T> clazz) {
        try {
            String jsonString = json.substring(11);
            int pageableIndex = jsonString.lastIndexOf(",\"pageable");
            jsonString = jsonString.substring(0, pageableIndex);
            JavaType type = mapper.getTypeFactory().constructCollectionType(Set.class, clazz);
            return mapper.readValue(jsonString, type);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }

}
