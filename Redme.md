nome do proxecto:
pasaxeirosvoosserializadooracle_3     (DEBE TER ESTE NOME EXACTAMENTE)

software dispoñible:

dispomos dos drivers para postgres

voos.sql  :  script necesario para crear as  taboas  en oracle que xa incluen as insercions de unha serie de datos (poden verse mais abaixo).

reservas : ficheiro de texto serializado chamado 'reservas'  que conten  unha serie de reservas de voos feitas por os pasaxeiros. Remata cun null (debes preguntar por el para rematar a  lectura de todas as reservas)

Reserva.java  :  clase java  que nos permite  manipular obxectos de tipo reservas.

Que se pide dentro do proxecto :

crea os metodos que consideres necesarios para poder desenvolver as seguentes accions (tamen podes facelo sen metodos):

a) lerReservas():  ler o ficheiro de texto serializado 'reservas'

b)  por cada reserva lida no apartado  a) debe :


b1)  debe aumentarse en 1 o numero de reservas para o pasaxeiro correspondente a cada reserva lida  (campo nreservas taboa pasaxeiros ).

b2) inserirse na taboa RESERVASFEITAS os datos correspondentes a reserva lida , lembrandose de calcular o campo prezoreseva   ( debe ser igual ao prezo do voo de ida mais o prezo do voo de volta , lembrar que o prezo dos voos atopanse na taboa voos de oracle) . Este apartado dividise en varios de cara a puntuacion :

	b.2.1) grabar codigo de reserva e dni
	b.2.2) grabar nome
	b.2.3) grabar prezo 




contidos iniciais do ficheiro serializado 'reservas':

codr  		identifica a reserva
dni  		indica pasaxeiro que fai a reserva
idvooida   	indica o voo de ida do pasaxeiro
idvoovolta 	indica o voo de volta do pasaxeiro

codr 	dni 		idvooida  	idvoovolta  	
1 	"361a" 		1 		2 		
2 	"362b" 		3 		4 		
3 	"361a" 		5 		6


contidos iniciais das taboas pasaxeiros e voos

pasaxeiros
DNI   NOME	      TELF	 CIDADE      NRESERVAS

361a  luis	      9861a	 vigo		     0
362b  ana	      9861b	 lugo		     0
363c  pedro	      9861c	 lugo		     0
364d  ana	      9861d	 vigo		     0

voos
VOO ORIXE	   	DESTINO		PREZO

1 	vigo 	   	estambul		150
2 	estambul	vigo 		  	200
3 	vigo 	  	 londres		 80
4 	londres	vigo 		     	90
5 	vigo 	   	lisboa		  	90
6 	lisboa	   	vigo 		  	100
7 	vigo 	  	viena		  	200
8 	viena	  	vigo 		  	250
9 	vigo 	  	tunez		   	160
10 	tunez	   	vigo 		  	150
11 	vigo 	  	paris		  	200
12 	paris	   	vigo 		   	90





b) contido da taboa pasaxeiros despois de ser actualizada cos datos procedentes das lecturas das reservas que se atopan no ficheiro serializado 'reservas'. (Vemos que o pasaxeiro "361a" ten 2 reservas porque realmente hai duas reservas  que son de dito pasaxeiro.)



PASAXEIROS

DNI   NOME	      TELF	 CIDADE      NRESERVAS

361a  luis	      9861a	 vigo		     2
362b  ana	      9861b	 lugo		     1
363c  pedro	      9861c	 lugo		     0
364d  ana	      9861d	 vigo		     0

c)
contido da taboa reservasfeitas despois de ser actualizada cos datos procedentes das lecturas das reservas que se atopan no ficheiro serializado 'reservas'

RESERVASFEITAS
codr 	dni 		nome 			prezoreserva  
1 	"361a" 		luis 			350 	
2 	"362b" 		ana		 	170 		
3 	"361a" 		luis 			190 		 
