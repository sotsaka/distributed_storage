sudo ip addr del 192.168.10.${1}/24 dev java${1}
sudo ip link delete java${1} type dummy
sudo rmmod dummy
