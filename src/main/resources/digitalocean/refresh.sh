cd /root/ScrappingService
git checkout master
git pull
systemctl stop scrapping.service
cp scrapping.service /etc/systemd/system/scrapping.service
mvn clean package
cp target/ScrappingService.jar /opt/prod/ScrappingService.jar

cd /root/MatchingService
git checkout develop
git pull
systemctl stop matching.service
cp matching.service /etc/systemd/system/matching.service
mvn clean package
cp target/MatchingService.jar /opt/prod/MatchingService.jar

cd /root/TroubleTicketService
git checkout master
git pull
systemctl stop tt.service
mvn clean package
cp target/TroubleTicket.jar /opt/prod/TroubleTicket.jar

cd /root/YouHockey
git checkout develop
git pull
systemctl stop youhockey.service
cp youhockey.service /etc/systemd/system/youhockey.service
mvn clean package
cp target/YourHockey.jar /opt/prod/YourHockey.jar

sudo systemctl daemon-reload
systemctl start youhockey.service
systemctl start matching.service
systemctl start tt.service
systemctl start scrapping.service

cd /root/JackNorthon
git checkout master
git pull
ng build --prod
sudo chmod -R 755 /var/www
cp -R dist/JackNorthon/* /var/www/html
