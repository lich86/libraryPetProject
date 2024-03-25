package com.chervonnaya.library.config.configurers;

import com.chervonnaya.library.dto.UserDTO;
import com.chervonnaya.library.model.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapperConfigurer implements BaseMapConfigurer{
    private final PasswordEncoder passwordEncoder;

    public UserMapperConfigurer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(ModelMapper mapper) {
        Converter<String, String> passwordConverter = context -> passwordEncoder.encode(context.getSource());

        PropertyMap<UserDTO, User> map = new PropertyMap<>() {
            @Override
            protected void configure() {
                using(passwordConverter).map(source.getPassword(),destination.getPassword());
            }
        };

        mapper.addMappings(map);
    }
}
