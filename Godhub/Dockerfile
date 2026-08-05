# 1. 자바 17 베이스 이미지 지정 (누락되었던 부분)
FROM eclipse-temurin:17-jdk-alpine

# 2. 작업 디렉터리 설정
WORKDIR /app

# 3. 프로젝트 전체 파일 복사 (gradlew, gradle, src 등 모두 포함)
COPY . .

# 4. gradlew 실행 권한 부여
RUN chmod +x gradlew

# 5. Gradle 빌드 수행 (테스트 제외)
RUN ./gradlew clean build -x test

# 6. 빌드된 jar 파일 실행 설정
ENTRYPOINT ["sh", "-c", "java -jar $(ls build/libs/*.jar | grep -v plain | head -n 1)"]