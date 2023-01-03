### 개발환경
- spring boot
- gradle
- thymeleaf
- mybatis
- oracle db
</p>

### <p><b>프로젝트 개요</b>
기존 여행에 대한 숙박비 부담을 줄이기 위한 프로젝트로<br>
사용자는 호텔을 예약하고 숙박 가능인원의 남는 자리를 <br>
타인과 같이 사용하는 것으로 숙박비에 대한 부담을 줄일 수 있습니다.<br>

### <p><b>프로젝트 담당</b>
로그인<br>![image](https://user-images.githubusercontent.com/103257619/208295433-a7c42772-cf12-44dc-85ad-aa4492a132ed.png)
아이디/비밀번호 찾기<br>![image](https://user-images.githubusercontent.com/103257619/208295804-d595d428-3241-4bab-94ea-a8736607dd71.png)
회원 가입<br>![회원가입 정상입력](https://user-images.githubusercontent.com/103257619/208295762-74ffb79f-322b-4cf8-a54a-e4cded8485ba.jpg)
회원 수정<br>![image](https://user-images.githubusercontent.com/103257619/208295446-2d99c27b-e969-4452-b50c-53bfcc73441e.png)
회원 탈퇴<br>
회원 로그<br>![image](https://user-images.githubusercontent.com/103257619/208295459-888642b5-339d-4a24-bfa4-1afd1295b489.png)

### <p><b>프로젝트 회고</b>
1. 기본적인 CRUD만 했다는 점... <br>
2. 내가 하는 작업에 대해서 왜? 라는 질문을 던지지 않았음...<br>
3. 비밀번호에 대해 암호화를 진행하고 DB에 입력해야 하는데 그렇게 하지 않았다는 점...<br>
4. 회원 로깅을 구현할때 인터셉터를 통해 DB에 자동으로 저장되게 했다는 점..(이렇게 하면 안됨...)<br>

### <p><b>프로젝트 작업 file</b>
-controller<br>
https://github.com/hwangjoonsoung/PEER/blob/develop/PEER/src/main/java/peer/controller/member<br>
-service<br>
https://github.com/hwangjoonsoung/PEER/tree/develop/PEER/src/main/java/peer/service/member<br>
-dao<br>
https://github.com/hwangjoonsoung/PEER/blob/develop/PEER/src/main/java/peer/dao/member<br>
-dto<br>
https://github.com/hwangjoonsoung/PEER/tree/develop/PEER/src/main/java/peer/model/member<br>
-interceptor<br>
https://github.com/hwangjoonsoung/PEER/blob/develop/PEER/src/main/java/peer/intercepter.java<br>
https://github.com/hwangjoonsoung/PEER/blob/develop/PEER/src/main/java/peer/InterceptorConfig.java<br>
-mapper<br>
https://github.com/hwangjoonsoung/PEER/blob/develop/PEER/src/main/resources/static/mapper/memberMapper.xml<br>
-templates<br>
https://github.com/hwangjoonsoung/PEER/tree/develop/PEER/src/main/resources/templates/member<br>
