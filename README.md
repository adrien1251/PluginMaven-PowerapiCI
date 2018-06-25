
# FR


# Powerapi MAVEN Plugin

Ce projet est un plugin Maven qui permet d'automatiser les traitements et envois de données liés à l'outil PowerAPI.

#Installation

Power API doit être préalablement installé sur votre machine(Uniquement systemes Linux) : 
https://github.com/Spirals-Team/powerapi/blob/master/README.md

- Cloner le dépot de ce plugin Maven

- Dans le repertoire du projet : 

  mvn install
  
- Dans le fichier de configuration Maven, ajouter cette ligne : 

  <pluginGroups>
       <pluginGroup>com.powerapi</pluginGroup>
   </pluginGroups>
   
- La commande permettant de lancer le processus : 

  mvn powerapi:runtest



# Contexte

Ce projet a été développé dans le cadre d'une collaboration entre Romain Rouvoy et Aurélien Bourdon de l'équipe Spirals de l'INRIA et Davidson SI Nord, afin de participer à la rédaction de la thèse de l'apprenti chercheur Chakib Belgaid.

# Autheurs

Adrien Deblock / Vincent Leclercq.
Ce projet a fait office de mission afin de mener à bien notre stage de fin d'étude au sein de Davidson Consulting.

## License

Ce projet est sous la licence MIT - voir le fichier [LICENSE.md](LICENSE.md) pour tout détails

------------------------------------------
# EN


# Powerapi MAVEN Plugin

This project is a Maven plugin that automates the processing and sending of data related to the PowerAPI tool.

#Installation

Power API must be installed on your machine beforehand(LINUX OS ONLY):
https://github.com/Spirals-Team/powerapi/blob/master/README.md

- Clone the repository of this Maven plugin

- In the project directory:

  mvn install
  
- In the Maven configuration file, add this line:

  <PluginGroups>
       <PluginGroup> com.powerapi </ pluginGroup>
   </ PluginGroups>
   
- The command to start the process:

  mvn powerapi: runtest



# Background

This project was developed in the framework of a collaboration between Romain Rouvoy and Aurélien Bourdon from the INRIA Spirals team and Davidson SI Nord, to participate in the writing of the thesis of apprentice researcher Chakib Belgaid.

# Autheurs

Adrien Deblock / Vincent Leclercq.
This project served as a mission to complete our end-of-study internship at Davidson Consulting.

## License

This project is under the MIT license - see file [LICENSE.md] (LICENSE.md) for details
