package org.med.youhospital.serverside.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HospitalReq {

    private String name;

    private String address;

    private String phone;
}
