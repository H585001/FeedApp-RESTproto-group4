package group4.feedapp.RESTproto.dao;

import java.util.Collection;

import group4.feedapp.RESTproto.model.IoTDevice;
import group4.feedapp.RESTproto.model.Poll;

public interface IoTDeviceDAO {
	IoTDevice createIoTDevice(IoTDevice device);
	IoTDevice createIoTDevice(String name, Poll linkedPoll);
	IoTDevice readIoTDevice(Long id);
	Collection<IoTDevice> readIoTDevices();
	IoTDevice updateIoTDevice(Long id, IoTDevice updatedDevice);
	IoTDevice deleteIoTDevice(Long id);
}
