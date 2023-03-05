# Students Marks Manager 
## Mini Projet JEE-Servlet-JSP avec Base de Données MySQL

Ce mini-projet utilise les technologies JEE, Servlet et JSP avec une base de données MySQL.
Notez que le dépôt contient l'ensemble du code source utilisé pour construire ce projet.
Le projet est exécuté sur un serveur Tomcat et est 
exporté en un fichier WAR. De plus, le projet est Dockerisé et est accompagné d'un conteneur MySQL. Les deux images Docker sont déployées sur 
Docker Hub et utilisées dans un fichier Docker Compose pour exécuter l'ensemble du projet. Ce guide fournit une série d'étapes 
détaillées pour exécuter le projet, que ce soit sur GitHub Codespaces, 
Play with Docker ou localement avec Docker.

### Prérequis
- Docker
- Git

### Clonage du projet
- Étape 1: Clonez le dépôt en utilisant la commande suivante:

```
git clone https://github.com/misterneo/students-marks-manager.git
```

- Étape 2: Accédez au répertoire du projet en utilisant la commande suivante:

```
cd students-marks-manager
```

### Exécution du projet

Pour exécuter ce projet, vous devez avoir Docker installé sur votre machine ou avoir accès à GitHub Codespaces qui offre un environnement 
cloud gratuit exécutant Docker, ou utiliser Play with Docker. Une fois cela fait, vous pouvez accéder au dossier /docker-hub qui contient 
un fichier docker-compose lié à 2 images Docker : l'une pour un serveur Tomcat qui inclut le fichier WAR du projet et un fichier de configuration
server.xml pour pouvoir exécuter le projet sur le chemin racine du serveur Tomcat, 
et l'autre pour une image MySQL avec le schéma de base de données du projet ajouté.

- Étape 1: Accédez au dossier /docker-hub en utilisant la commande suivante:

```
cd /docker-hub
```

- Étape 2: Démarrer les conteneurs Docker en utilisant la commande suivante:

```
docker-compose up -d
```

- Étape 3: Accédez à l'application en naviguant vers http://localhost:8888/. 

Le contexte est configuré pour être exécuté à la racine du serveur Tomcat.

> Si vous avez réussi à exécuter le projet, vous verrez d'abord ce qui suit :

![Screen Shot 2023-03-05 at 12 24 55](https://user-images.githubusercontent.com/25622326/222957689-85e9b6a3-2246-4a5c-b8a6-de01525aa5b5.png)


> Et lorsque vous cliquez sur le bouton "Get Started", vous verrez des statistic des étudiants et des matières totales insérées dans la base de données, ainsi que la moyenne générale de la classe.

![Screen Shot 2023-03-05 at 12 11 26](https://user-images.githubusercontent.com/25622326/222957406-7901c1d7-889f-4121-8244-df6b1c6d3c25.png)

> En dessous montre une liste des étudiants, et une liste des matières:

![Screen Shot 2023-03-05 at 12 11 37](https://user-images.githubusercontent.com/25622326/222957864-f8736a21-efb4-4be1-bd80-82ce357fefec.png) ![Screen Shot 2023-03-05 at 12 11 49](https://user-images.githubusercontent.com/25622326/222957865-28033c70-f09e-453b-9e47-373ca44b4db9.png)

> Et finalement un tableau des étudiants avec leurs notes pour chaque matière et leur moyenne, ainsi que la possibilité de modifier les scores

![Screen Shot 2023-03-05 at 12 12 07](https://user-images.githubusercontent.com/25622326/222958249-f12e621f-2628-4c04-bc97-cf7a87154e18.png)
![Screen Shot 2023-03-05 at 12 12 28](https://user-images.githubusercontent.com/25622326/222958290-e2a5f7d4-b824-4149-8eab-429681704e46.png)



- Étape 4: Arrêtez les conteneurs Docker en utilisant la commande suivante:

```
docker-compose down
```

### Conclusion
C'est tout! Vous avez maintenant exécuté avec succès le projet avec une base de données MySQL à l'aide de Docker.
