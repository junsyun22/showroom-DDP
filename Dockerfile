# Java 17 기반 이미지 사용
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일을 컨테이너로 복사
COPY target/showroom-DDP-0.0.1-SNAPSHOT.jar app.jar

# 포트 12450 노출
EXPOSE 12450

# 애플리케이션 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]
