package com.osapps.chat.socket;

import com.rocketchat.common.data.model.BaseRoom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sachin on 29/7/17.
 */

// TODO: 29/7/17 add methods for getting rooms based on favourites, one to one and groups (public and private)
// TODO: 29/7/17 might have to make separate arraylist for each type, seems little impossible, better to keep generic
public class ChatRoomFactory {

    private RocketChatClient client;
    private List<ChatRoom> rooms;

    public ChatRoomFactory(RocketChatClient client) {
        this.client = client;
        rooms = new ArrayList<>();
    }

    private ChatRoom createChatRoom(BaseRoom room) {
        return new ChatRoom(client, room);
    }

    public ChatRoomFactory createChatRooms(List<? extends BaseRoom> roomObjects) {
        removeAllChatRooms();
        for (BaseRoom room : roomObjects) {
            rooms.add(createChatRoom(room));
        }
        return this;
    }

    public ChatRoomFactory addChatRoom(BaseRoom room) {
        if (getChatRoomByName(room.name()) == null) {
            ChatRoom newRoom = createChatRoom(room);
            rooms.add(newRoom);
        }
        return this;
    }

    public List<ChatRoom> getChatRooms() {
        return rooms;
    }

    public ChatRoom getChatRoomByName(String roomName) {
        for (ChatRoom room : rooms) {
            if (room.getRoomData().name() != null
                    && roomName.contentEquals(room.getRoomData().name())) {
                return room;
            }
        }
        return null;
    }

    public ChatRoom getChatRoomById(String roomId) {
        for (ChatRoom room : rooms) {
            if (roomId.contentEquals(room.getRoomData().roomId())) {
                return room;
            }
        }
        return null;
    }

    public Boolean removeChatRoomByName(String roomName) {
        for (ChatRoom room : rooms) {
            if (room.getRoomData().name() != null
                    && roomName.contentEquals(room.getRoomData().name())) {
                return rooms.remove(room);
            }
        }
        return false;
    }

    public Boolean removeChatRoomById(String roomId) {
        for (ChatRoom room : rooms) {
            if (room.getRoomData().roomId().equals(roomId)) {
                return rooms.remove(room);
            }
        }
        return false;
    }

    public Boolean removeChatRoom(ChatRoom room) {
        return rooms.remove(room);
    }

    public void removeAllChatRooms() {
        rooms.clear();
    }
}
