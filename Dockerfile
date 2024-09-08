# 베이스 이미지로 OpenJDK 17 사용
FROM eclipse-temurin:17 as jre-build

# 작업 디렉터리 설정

RUN mkdir /opt/app
# 프로젝트의 jar 파일을 Docker 컨테이너 안으로 복사
COPY build/libs/showroom-0.0.1-SNAPSHOT.jar /opt/app/app.jar

# 컨테이너에서 실행될 명령어 설정

# 외부에 노출할 포트 설정 (docker-compose.yml에서 매핑할 포트)
EXPOSE 12450

ENTRYPOINT ["java","-jar","/opt/app/app.jar"]
