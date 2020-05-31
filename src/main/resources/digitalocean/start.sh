cd ScrappingService
git checkout master
git pull
mvn clean package
java -jar target/ScrappingService.jar &

cd ../MatchingService
git checkout master
git pull
mvn clean package
java -jar target/MatchingService.jar &

cd ../YouHockey
git checkout master
git pull
systemctl stop youhockey.service
cp youhockey.service /etc/systemd/system/youhockey.service
mvn clean package
cp target/YourHockey.jar /opt/prod/YourHockey.jar

cd ../JackNorthon
git checkout master
git pull
ng serve --host=161.35.70.99 &

sudo systemctl daemon-reload
systemctl start youhockey.service
