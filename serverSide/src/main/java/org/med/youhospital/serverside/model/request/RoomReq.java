package org.med.youhospital.serverside.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.med.youhospital.serverside.model.enums.RoomType;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomReq {

    @PositiveOrZero(message = "roomNum should be positive")
    private Integer roomNum;

    @NotBlank(message = "location should be not blank")
    private String location;

    private RoomType roomType;

    @NotNull(message = "departmentId should be not null")
    private UUID departmentId;


}
