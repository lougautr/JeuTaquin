make:
	javac -d build/ src/modele/* src/vue/* src/controle/*
	java -cp build controle.Fenetre

