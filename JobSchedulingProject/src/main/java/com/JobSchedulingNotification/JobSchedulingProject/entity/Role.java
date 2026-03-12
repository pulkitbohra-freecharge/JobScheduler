package com.JobSchedulingNotification.JobSchedulingProject.entity;

import com.JobSchedulingNotification.JobSchedulingProject.converter.ApiListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity

@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Convert(converter = ApiListConverter.class)
    @Column(name="allowed_apis",columnDefinition = "TEXT")
    private List<String> allowedApis;

    @Transient
    private Map<String, List<String>> apiPermissionMap;

    public void buildPermissionMap(){

        if(allowedApis==null){
            apiPermissionMap= new HashMap<>();
            return;
        }

        apiPermissionMap=allowedApis.stream().map(api->api.split(":",2)).collect(Collectors.groupingBy(arr->arr[0], Collectors.mapping(arr->arr[1],Collectors.toList())));
    }

}
