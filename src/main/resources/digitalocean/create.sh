sudo apt-get update

# docker
sudo apt install docker.io
sudo curl -L "https://github.com/docker/compose/releases/download/1.24.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# front
git clone https://github.com/AbrosimovVladislav/JackNorthon
sudo apt install npm
sudo npm install -g @angular/cli@9.1.1
sudo npm cache clean -f
sudo npm install -g n
sudo n 12.14.1
sudo ng update @angular/core@9.1.2
cd JackNorthon || sudo ng build --prod || cd ..
docker build -t front:1 JackNorthon
docker run --name front -p 80:80 -v /root/JackNorthon/nginx.conf:/etc/nginx/nginx.conf -d front:1

# postgresql
wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -
echo "deb http://apt.postgresql.org/pub/repos/apt/ $(lsb_release -cs)-pgdg main" | sudo tee  /etc/apt/sources.list.d/pgdg.list
sudo apt update
sudo apt -y install postgresql-12 postgresql-client-12
sudo su - postgres
psql -c "ALTER USER postgres WITH PASSWORD 'root'"
psql -c "CREATE DATABASE gunmarket"
exit

# back
git clone https://github.com/AbrosimovVladislav/Project_GunMarket.git
sudo apt-get install default-jdk
sudo apt install maven
cd Project_GunMarket || git checkout develop || mvn clean package || java -jar target/GunMarket.jar
