package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

public class WindowDto {
    private Long id;
    private String name;
    private WindowStatus windowStatus;
   private String roomName;
   private Long roomId;
//    private Long  buildingId;
//     private String room;

    public WindowDto() { }

    public WindowDto(Window window) {
        this.id = window.getId();
        this.name = window.getName();
        this.windowStatus = window.getWindowStatus();
       this.roomId = window.getRoom().getId();
       this.roomName = window.getRoom().getName();
//        this.buildingId = window.getBuilding().getId();
        // this.room = new RoomDto(window.getRoom());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

public String getRoomName() {
       return roomName;
   }

   public void setRoomName(String roomName) {
       this.roomName = roomName;
   }

//    public String getRoom() {
//        return room;
//    }
//
//    public void setRoom(String room) {
//        this.room = room;
//    }

   public Long getRoomId() {
       return roomId;
   }

public WindowStatus getWindowStatus() {
	return windowStatus;
}

public void setWindowStatus(WindowStatus windowStatus) {
	this.windowStatus = windowStatus;
}


   

//   public Long getBuildingId() {
//       return buildingId;
//   }
//
//    public void setBuildingId(Long buildingId) {
//        this.buildingId = buildingId;
//    }

//    public void setRoomId(Long roomId) {
//        this.roomId = roomId;
//    }
}