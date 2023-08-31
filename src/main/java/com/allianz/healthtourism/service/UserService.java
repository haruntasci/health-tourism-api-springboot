package com.allianz.healthtourism.service;

import com.allianz.healthtourism.database.entity.Role;
import com.allianz.healthtourism.database.entity.User;
import com.allianz.healthtourism.database.repository.RoleRepository;
import com.allianz.healthtourism.database.repository.UserRepository;
import com.allianz.healthtourism.mapper.UserMapper;
import com.allianz.healthtourism.model.requestDTO.UserRequestDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void saveAdminUser(UserRequestDTO requestDTO) {
        User adminUser = userMapper.requestDtoToEntity(requestDTO);
        adminUser.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        Set<Role> roleList = new HashSet<>();
        Role admin = roleRepository.findByName("admin").orElse(null);
        if (admin != null) {
            roleList.add(admin);
        } else {
            Role adminRole = new Role();
            adminRole.setName("admin");
            adminRole.setDescription("Efficient and experienced administrator.");
            roleRepository.save(adminRole);
            roleList.add(adminRole);
        }
        adminUser.setRoles(roleList);
        userRepository.save(adminUser);
    }

    public void saveDoctorUser(UserRequestDTO requestDTO) {
        User doctorUser = userMapper.requestDtoToEntity(requestDTO);
        doctorUser.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        Set<Role> roleList = new HashSet<>();
        Role doctor = roleRepository.findByName("doctor").orElse(null);
        if (doctor != null) {
            roleList.add(doctor);
        } else {
            Role doctorRole = new Role();
            doctorRole.setName("doctor");
            doctorRole.setDescription("Skilled and compassionate doctor.");
            roleRepository.save(doctorRole);
            roleList.add(doctorRole);
        }
        doctorUser.setRoles(roleList);
        userRepository.save(doctorUser);
    }

    public void savePatientUser(UserRequestDTO requestDTO) {
        User patientUser = userMapper.requestDtoToEntity(requestDTO);
        patientUser.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        Set<Role> roleList = new HashSet<>();
        Role patient = roleRepository.findByName("doctor").orElse(null);
        if (patient != null) {
            roleList.add(patient);
        } else {
            Role patientRole = new Role();
            patientRole.setName("patient");
            patientRole.setDescription("Engaged and cooperative patient.");
            roleRepository.save(patientRole);
            roleList.add(patientRole);
        }
        patientUser.setRoles(roleList);
        userRepository.save(patientUser);
    }

}
