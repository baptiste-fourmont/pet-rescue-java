# projet-ip

BRANCH First
---> https://docs.github.com/en/free-pro-team@latest/github/authenticating-to-github/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent

Gérer tes clefs RSA ou OPENSSH avec le projet; ca t'évitera de faire ssh-add ou d'éval ton agent à chaque fois ca économise grave du temps et c'est archi pratique

Tu crées un fichier touch ~/.ssh/config


Host github.com

    HostName github.com
    IdentityFile ~/.ssh/taclefprojetpourip 

Host gaufre.informatique.univ-paris-diderot.fr

     HostName gaufre.informatique.univ-paris-diderot.fr
     IdentityFile ~/.ssh/id_rsa_visulog
                                              
