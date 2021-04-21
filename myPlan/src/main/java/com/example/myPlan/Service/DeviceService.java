package com.example.myPlan.Service;

import com.example.myPlan.Entities.Collaborator;
import com.example.myPlan.Entities.Desk;
import com.example.myPlan.Entities.Device;
import com.example.myPlan.Repository.CollaboratorRepository;
import com.example.myPlan.Repository.DeviceRepository;

import java.util.Optional;

public class DeviceService {
    public static boolean saveDevice(String name, String type, String number, Collaborator collaborator, Desk desk, DeviceRepository deviceRepository){
        try {
            Device device = new Device(name, type, number, collaborator, desk);
            deviceRepository.save(device);

            return true;
        } catch (Exception e){

            return false;
        }
    }

    public static boolean updateDevice(Device device, String name, String type, String number, Collaborator collaborator, Desk desk, DeviceRepository deviceRepository){

        try {
            Optional<Device> deviceToUpdate = deviceRepository.findById(device.getId());
            if (deviceToUpdate.isPresent()) {
                Device deviceUpdated = deviceToUpdate.get();
                deviceUpdated.setName(name);
                deviceUpdated.setType(type);
                deviceUpdated.setNumber(number);
                deviceUpdated.setCollaborator(collaborator);
                deviceUpdated.setDesk(desk);

                deviceRepository.save(deviceUpdated);

            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean deleteDevice(Device device, DeviceRepository deviceRepository){
        try {
            deviceRepository.delete(device);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Device getDeviceByName(String name, DeviceRepository deviceRepository) {
        try {
            Optional<Device> optDevice = deviceRepository.findByNameLike(name);
            return optDevice.get();
        }catch (Exception e) {
            return null;
        }
    }
}