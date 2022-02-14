# Java Shell

Deber 1 de Sistemas Operativos, un Shell simple realizado en Java.

### Miembros

* José Cadena - 00206243
* Oscar Chriboga - 00205604
* Raí Díaz - 00204686
* Christian López - 00200613

### Lista de archivos

| Nombre                    | Descripcion                                                                                                                               |
|---------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| `src/CommandHistory.java` | La clase que maneja la funcionalidad de historial para los comandos.                                                                      |
| `src/CommandRunner.java`  | La clase que ejecuta los subprocesos necesarios para cada comando.                                                                        |
| `src/Shell.java`          | El entrypoint del programa, contiene un bucle infinito que corre los comandos con `CommandRunner` hasta que se ingrese el comando `exit`. |
| `src/Utils.java`          | Una clase estática que contiene algunas utilidades para parsear los argumentos de un comando.                                             |

### Contribución al trabajo

Realizamos el proyecto en conjunto utilizando Code With Me para IntelliJ IDEA CE en Zoom durante los horarios de laboratorio en clase.
Inicialmente queríamos utilizar GitHub para que cada uno implemente una funcionalidad en su propio tiempo, pero finalmente no fue muy necesario porque
se acabó el ejercicio durante las horas de laboratorio.

Empezamos todos a realizar un esqueleto base (`CommandRunner` y `Shell`) y luego cada uno implementó 2 funciones del `CommandRunner` en la sesión de CodeWithMe.

### Lógica que se siguió

Para el mayor número de comandos posibles se utilizó un `ProcessBuilder` con el comando deseado.
Se utilizó el método `ProcessBuilder.inheritIO()` para pipear el `stdout` y `stderr` del subproceso al del proceso principal de Java.

* Para el comando `cd` se decidió de mantener una variable en el `CommandRunner` que guarde el directorio actual en donde
se encuentra el usuario y luego emular el cambio de directorio manualmente.
* En el caso de `ipconfig` e `ifconfig`, se decidió que utilizar el comando `ipconfig` en Unix lance un error y que `ifconfig` en Windows también.
* Para el caso de `history`, al no estar disponible en Windows, se implementó su funcionamiento con el `CommandHistory`, para utilizarlo, se debe ejecutar: `history -n 10`, el cual mostrará los primeros 10 comandos de los últimos 20 ejecutados.


### Instrucciones de ejecución

Para compilar el programa:

    javac ./src/**/*.java

Para correr el programa:

    java Shell

### Ejemplo de output

```text
/Users/moonstar/Google Drive/USFQ/Semestre 7/Sistemas Operativos CMP 4003/Deber_1 > echo hi
hi

/Users/moonstar/Google Drive/USFQ/Semestre 7/Sistemas Operativos CMP 4003/Deber_1 > ping -n 3 google.com
PING google.com (142.250.217.238): 56 data bytes
64 bytes from 142.250.217.238: icmp_seq=0 ttl=115 time=68.972 ms
64 bytes from 142.250.217.238: icmp_seq=1 ttl=115 time=77.219 ms
64 bytes from 142.250.217.238: icmp_seq=2 ttl=115 time=67.668 ms

--- google.com ping statistics ---
3 packets transmitted, 3 packets received, 0.0% packet loss
round-trip min/avg/max/stddev = 67.668/71.286/77.219/4.229 ms

/Users/moonstar/Google Drive/USFQ/Semestre 7/Sistemas Operativos CMP 4003/Deber_1 > ifconfig
lo0: flags=8049<UP,LOOPBACK,RUNNING,MULTICAST> mtu 16384
	options=1203<RXCSUM,TXCSUM,TXSTATUS,SW_TIMESTAMP>
	inet 127.0.0.1 netmask 0xff000000 
	inet6 ::1 prefixlen 128 
	inet6 fe80::1%lo0 prefixlen 64 scopeid 0x1 
	nd6 options=201<PERFORMNUD,DAD>
gif0: flags=8010<POINTOPOINT,MULTICAST> mtu 1280
stf0: flags=0<> mtu 1280
en0: flags=8863<UP,BROADCAST,SMART,RUNNING,SIMPLEX,MULTICAST> mtu 1500
	options=400<CHANNEL_IO>
	ether 60:f8:1d:ab:57:30 
	inet6 fe80::40d:adb6:3e3:225%en0 prefixlen 64 secured scopeid 0x4 
	inet 172.21.157.86 netmask 0xfffffc00 broadcast 172.21.159.255
	nd6 options=201<PERFORMNUD,DAD>
	media: autoselect
	status: active
en1: flags=8963<UP,BROADCAST,SMART,RUNNING,PROMISC,SIMPLEX,MULTICAST> mtu 1500
	options=460<TSO4,TSO6,CHANNEL_IO>
	ether 82:0f:1c:bb:f4:c0 
	media: autoselect <full-duplex>
	status: inactive
en2: flags=8963<UP,BROADCAST,SMART,RUNNING,PROMISC,SIMPLEX,MULTICAST> mtu 1500
	options=460<TSO4,TSO6,CHANNEL_IO>
	ether 82:0f:1c:bb:f4:c1 
	media: autoselect <full-duplex>
	status: inactive
bridge0: flags=8863<UP,BROADCAST,SMART,RUNNING,SIMPLEX,MULTICAST> mtu 1500
	options=63<RXCSUM,TXCSUM,TSO4,TSO6>
	ether 82:0f:1c:bb:f4:c0 
	Configuration:
		id 0:0:0:0:0:0 priority 0 hellotime 0 fwddelay 0
		maxage 0 holdcnt 0 proto stp maxaddr 100 timeout 1200
		root id 0:0:0:0:0:0 priority 0 ifcost 0 port 0
		ipfilter disabled flags 0x0
	member: en1 flags=3<LEARNING,DISCOVER>
	        ifmaxaddr 0 port 5 priority 0 path cost 0
	member: en2 flags=3<LEARNING,DISCOVER>
	        ifmaxaddr 0 port 6 priority 0 path cost 0
	nd6 options=201<PERFORMNUD,DAD>
	media: <unknown type>
	status: inactive
p2p0: flags=8843<UP,BROADCAST,RUNNING,SIMPLEX,MULTICAST> mtu 2304
	options=400<CHANNEL_IO>
	ether 02:f8:1d:ab:57:30 
	media: autoselect
	status: inactive
awdl0: flags=8943<UP,BROADCAST,RUNNING,PROMISC,SIMPLEX,MULTICAST> mtu 1484
	options=400<CHANNEL_IO>
	ether 3e:80:de:eb:86:11 
	inet6 fe80::3c80:deff:feeb:8611%awdl0 prefixlen 64 scopeid 0x9 
	nd6 options=201<PERFORMNUD,DAD>
	media: autoselect
	status: active
llw0: flags=8863<UP,BROADCAST,SMART,RUNNING,SIMPLEX,MULTICAST> mtu 1500
	options=400<CHANNEL_IO>
	ether 3e:80:de:eb:86:11 
	inet6 fe80::3c80:deff:feeb:8611%llw0 prefixlen 64 scopeid 0xa 
	nd6 options=201<PERFORMNUD,DAD>
	media: autoselect
	status: active
utun0: flags=8051<UP,POINTOPOINT,RUNNING,MULTICAST> mtu 1380
	inet6 fe80::b827:da0:3e06:1b42%utun0 prefixlen 64 scopeid 0xb 
	nd6 options=201<PERFORMNUD,DAD>
utun1: flags=8051<UP,POINTOPOINT,RUNNING,MULTICAST> mtu 2000
	inet6 fe80::87a4:2868:108:206%utun1 prefixlen 64 scopeid 0xc 
	nd6 options=201<PERFORMNUD,DAD>
utun2: flags=8051<UP,POINTOPOINT,RUNNING,MULTICAST> mtu 1380
	inet6 fe80::76b2:b9d3:1b87:44de%utun2 prefixlen 64 scopeid 0xd 
	nd6 options=201<PERFORMNUD,DAD>
utun3: flags=8051<UP,POINTOPOINT,RUNNING,MULTICAST> mtu 1380
	inet6 fe80::4c23:cc09:6054:fb81%utun3 prefixlen 64 scopeid 0xe 
	nd6 options=201<PERFORMNUD,DAD>
utun4: flags=8051<UP,POINTOPOINT,RUNNING,MULTICAST> mtu 1380
	inet6 fe80::aa43:aab8:ea8f:81e3%utun4 prefixlen 64 scopeid 0x10 
	nd6 options=201<PERFORMNUD,DAD>
utun5: flags=8051<UP,POINTOPOINT,RUNNING,MULTICAST> mtu 1380
	inet6 fe80::b7d8:c28e:b6db:483%utun5 prefixlen 64 scopeid 0x11 
	nd6 options=201<PERFORMNUD,DAD>
utun6: flags=8051<UP,POINTOPOINT,RUNNING,MULTICAST> mtu 1380
	inet6 fe80::dbb7:5e7e:cf2a:b2b8%utun6 prefixlen 64 scopeid 0x12 
	nd6 options=201<PERFORMNUD,DAD>
utun7: flags=8051<UP,POINTOPOINT,RUNNING,MULTICAST> mtu 1380
	inet6 fe80::c06b:a120:27f1:968b%utun7 prefixlen 64 scopeid 0x13 
	nd6 options=201<PERFORMNUD,DAD>

/Users/moonstar/Google Drive/USFQ/Semestre 7/Sistemas Operativos CMP 4003/Deber_1 > history 
1	echo hi
2	ping -n google.com
3	ping -n 3 google.com
4	ifconfig
5	history 

/Users/moonstar/Google Drive/USFQ/Semestre 7/Sistemas Operativos CMP 4003/Deber_1 > cd ../../ ^ ls
Books
Coreano Basico 1 LNA 2401
Diseno de Interfaces Fisicas DIM 3002
Programacion para Diseno 3 DIM 3103
Proyectos Gerencia y Analisis IIN 4011
Redes 1 CMP 4201
Sistemas Operativos CMP 4003
Syllabi

/Users/moonstar/Google Drive/USFQ/Semestre 7 > cd / ^ ls
Applications
Library
System
Users
Volumes
bin
cores
dev
etc
home
opt
private
sbin
tmp
usr
var

/ >
```