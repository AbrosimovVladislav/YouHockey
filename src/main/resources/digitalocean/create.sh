sudo apt-get update

# front
git clone https://github.com/AbrosimovVladislav/JackNorthon
cd JackNorthon/
git checkout master
curl -sL https://deb.nodesource.com/setup_12.x | sudo -E bash -
sudo apt -y install nodejs
sudo npm install -g @angular/cli@9.1.1
sudo npm install
sudo ng build --prod
cd ..

## postgresql
#echo "deb http://apt.postgresql.org/pub/repos/apt/ $(lsb_release -cs)-pgdg main" | sudo tee /etc/apt/sources.list.d/pgdg.list
#wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -
#sudo apt update
#sudo apt -y install postgresql-12 postgresql-client-12
#sudo -u postgres psql -c "ALTER USER postgres WITH PASSWORD 'root'"
#sudo -u postgres psql -c "CREATE DATABASE gunmarket"
#
## back
#git clone https://github.com/AbrosimovVladislav/YouHockey.git
#sudo apt -y install default-jdk
#sudo apt -y install maven
#cd YouHockey/
#git checkout master
#mvn clean package
#cd ..
#
## docker
#sudo apt -y install docker.io
#sudo curl -L "https://github.com/docker/compose/releases/download/1.25.5/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
#sudo chmod +x /usr/local/bin/docker-compose
#cd JackNorthon/
#docker-compose up --build -d
#
## network
#ufw allow 8080/tcp
#uwf allow 80/tcp
