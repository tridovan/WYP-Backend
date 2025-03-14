👙👙👙👙nếu muốn dùng database local
(Mở file appliaction.properties lên bật dòng database local lên)
*chạy docker compose(sẵn jdk, maven, mysql trong images)
docker-compose up -d

==================================
👙👙👙👙nếu muốn dùng database railway
*build image
docker build -t backendservice:version0.1 .

*xem những images
docker images

*tạo container
docker run -d --name backendservice -p 8080:8080 backendservice:version0.1

*xem những container đang chạy
docker ps

*Lệnh xem logs của container
docker logs backendservice

*Lệnh dừng container
docker stop backendservice

*Lệnh xóa contaier
docker rm backendservice

=================================
🐕🐕🐕🐕🐕🐕🐕🐕
sử dụng mysql workbench cho dễ thao tác:
mysql://root:odexvFkedNZRgcRynVVwFTVUxPQPWchO@crossover.proxy.rlwy.net:37767/railway
1. tạo mới 1 connection
2. Connection name tùy ý
3. Điền Hostname: crossover.proxy.rlwy.net
4. Điền Password: odexvFkedNZRgcRynVVwFTVUxPQPWchO
5. Điền Port: 37767
6. Ok