package com.example.demo.service;

import com.example.demo.dto.ApplicationDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Application;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationService applicationService;

    // Convert UserDTO to User
    private User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setUserName(dto.getUserName());
        user.setUserEmail(dto.getUserEmail());
        user.setUserPassword(dto.getUserPassword());
        user.setUserRole(dto.getUserRole());

        // Fetch the application by appId
        ApplicationDTO applicationDTO = applicationService.getApplicationById(dto.getAppId());

        Application application = new Application();
        application.setAppId(applicationDTO.getAppId());
        user.setApplication(application);

        return user;
    }

    // Convert User to UserDTO
    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        dto.setUserEmail(user.getUserEmail());
        dto.setUserPassword(user.getUserPassword());
        dto.setUserRole(user.getUserRole());
        dto.setAppId(user.getApplication().getAppId());
        return dto;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public List<UserDTO> findByFilterSortAndPage(String sortBy, String direction,
                                                 int limit, int offset, String filterType, String filterValue) {
        List<User> users = userRepository.findByFilterSortAndPage(sortBy, direction, limit, offset, filterType,
                filterValue);
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id).orElse(null);
        return convertToDTO(user);
    }

    public UserDTO updateUser(int id, UserDTO updatedUserDTO) {
        if (userRepository.existsById(id)) {
            User user = convertToEntity(updatedUserDTO);
            user.setUserId(id);
            User savedUser = userRepository.save(user);
            return convertToDTO(savedUser);
        }
        return null;
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
