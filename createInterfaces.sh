
sudo modprobe dummy
sudo ip link add java${1} type dummy
sudo ip addr add 192.168.10.${1}/24 dev java${1}
sudo ip link set dev java${1} up
