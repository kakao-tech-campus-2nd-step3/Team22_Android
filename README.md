# 2앱2조 - SOUNDARY
## SOUNDARY란?
> ***위젯 기반 바운더리 노래 공유의 폐쇄적 SNS앱***
<img width="256" alt="all_logo_image" src="https://github.com/user-attachments/assets/a70b4443-ed0a-42c2-96ee-fe898a00464c">


### 배포 주소
- BE : https://api.soundary.kro.kr
- Android : https://m.onestore.co.kr/ko-kr/apps/appsDetail.omp?prodId=0000779563
  

### 시스템 설계도
> **시스템 아키텍처**
![KakaoTalk_Photo_2024-11-14-17-18-45](https://github.com/user-attachments/assets/c97ab53f-43b0-408f-ab74-d191ae67d637)
![KakaoTalk_Photo_2024-11-09-16-45-58](https://github.com/user-attachments/assets/59c2afe7-54e1-438f-a9a6-05d104a4ad6d)

> **ERD**
![KakaoTalk_Photo_2024-11-09-16-23-12](https://github.com/user-attachments/assets/6fed1e60-3e37-4b5d-a2d3-6818a30c8ee6)

> **API 문서**
> - https://api.soundary.kro.kr/swagger-ui/index.html#/
> <img width="500"  alt="스크린샷 2024-11-09 오후 4 45 07" src="https://github.com/user-attachments/assets/d1cd245d-8b2f-43b4-86d5-306308bab5ed">
> <img width="500" alt="스크린샷 2024-11-09 오후 4 45 16" src="https://github.com/user-attachments/assets/60a2c1fa-fb7c-40c5-bc34-17e56336529a">
> <img width="500" alt="스크린샷 2024-11-09 오후 4 45 25" src="https://github.com/user-attachments/assets/9c3dd9f2-b26a-456b-a1cd-7b2e6f80d9d6">
> <img width="500"  alt="스크린샷 2024-11-09 오후 4 45 34" src="https://github.com/user-attachments/assets/47cb9ea8-953f-4132-b5f9-16de0b2ec404">


### SOUNDARY에 관하여
(1) SOUDNARY 기획 의도 
- 현재 SNS는 과다 개방으로 불특정 다수에게 나의 취향이 공유되어 부담스러운 경우가 많으며, 음악 앱 사용 시 유료 구독 결제를 전제로만 원하는 음악을 공유하고 주고받을 수 있다.
- 보다 간단한 프로세스로 특정 원하는 사람에게만 내가 원하는 음악을 빠르게 공유하고 주고받으며 취향과 일상을 공유하는, 보다 폐쇄적인 SNS가 필요한 시대이다.

(2) 목적
- 폐쇄적 관계의 음악 공유로 현대인들의 소통적 피로감을 덜고, 위젯을 통해 프로세스를 단순화 시켜 즉각적인 주고받음이 가능하도록 하자. 

(3) 주요 기능
> 위젯을 통한 즉각적인 의사소통
<p align="center">
  <img src="https://github.com/user-attachments/assets/2c1f46bb-987c-4e45-b941-cad925f917cb" width="30%">
  <img src="https://github.com/user-attachments/assets/35c3ea19-8dbd-4a84-982b-192f67b07c00" width="30%">
</p>

- 음악을 공유받았을 때 단순히 알림을 확인하는 것이 아닌, 변경된 위젯의 앨범 커버를 통해 어떤 음악을 공유받았는지 알 수 있다.
- 또한, 앨범 커버가 바로 바뀜으로써, 빠른 시간 안에 변화하는 콘텐츠에 익숙한 사용자들에게 지루함이 아닌, 즐거움을 줄 수 있다.
- 해당 위젯의 앨범 커버를 눌렀을 때 바로 앱에 접속이 가능하도록 프로세스를 단순화 시켰다.


> 5초 이상 재생 시 공유한 사용자의 메시지 확인 가능 기능
<p align="center">
  <img src="https://github.com/user-attachments/assets/f9c8ed8b-ee3a-4d49-a03f-976ef8586ff0" width="30%">
  <img src="https://github.com/user-attachments/assets/fa11705c-b7bd-4d7e-9bdf-e6c60427506b" width="30%">
  <img src="https://github.com/user-attachments/assets/29007369-3a36-4a0c-afe9-fc270fda5eaf" width="30%">
</p>

- 공유한 음악의 하이라이트를 들으며 사용자가 '나'에게 보내는 메시지를 확인할 수 있도록 한다.
- 일반적인 SNS앱에서 텍스트로 주고받는 것보다 음악을 들으며 해당 음악의 분위기와 음악을 통한 기억을 바탕으로 다른 SNS앱과 차별점을 두었다.


> 바텀 시트를 이용한 공유 서비스
<p align="center">
  <img src="https://github.com/user-attachments/assets/9ad4f3ed-a02b-4a42-a6b9-99efaea3464e" width="30%">
  <img src="https://github.com/user-attachments/assets/b0ee2e4d-ed1e-4591-92f6-7f789e4d32e2" width="30%">
  <img src="https://github.com/user-attachments/assets/8149a5de-12ec-4262-8e87-86ec3ac4a66d" width="30%">
</p>

- 한 화면에서 앨범 정보를 확인하며 공유할 수 있도록 프로세스를 단순화시켜 사용자가 편리하게 사용할 수 있도록 했다.


> 20명으로 한정된 친구 목록
<p align="center">
  <img src="https://github.com/user-attachments/assets/44e51b97-5351-4e9e-ba6e-0bdf54e698d3" width="30%">
</p>

- 폐쇄적인 SNS앱이므로 정말 친한 지인들끼리만 음악을 공유할 수 있도록 하였다.
- 다수보다 소수로 커뮤니티를 만들어 피곤함 없이 정말 재미와 휴식을 위해 소통할 수 있도록 하였다.


> 라벨을 통한 취향 공유
<p align="center">
  <img src="https://github.com/user-attachments/assets/e45a335f-e023-4398-b6c3-0d6f03f7757d" width="30%">
  <img src="https://github.com/user-attachments/assets/12634116-6d8d-4cf6-8dac-cf1018afdd21" width="30%">
  <img src="https://github.com/user-attachments/assets/1fd61f5d-2c39-4b8c-9fe2-cea28965be16" width="30%">
</p>

- 회원가입 시 본인의 취향인 음악 카테고리를 필수로 선택하여 지인들끼리도 서로의 음악을 공유하는 부가적인 기능을 발휘할 수 있다.

(4) 사용 설명서
- 첫 회원가입 유저를 기준으로 한 사용설명서입니다.
- 구체적인 앱 사용 과정을 보고싶다면, 아래의 **시연영상**을 참고해주세요.
1. 배경화면에 ‘Soundary’ 위젯을 추가한다. (디폴트 이미지가 뜨면 성공)
2. `카카오톡으로 로그인하기`를 클릭한다.
3. 카카오 로그인을 완료 시, `회원가입` 페이지로 이동한다.
4. 닉네임 입력, 라벨 선택, 약관동의를 체크 후 `계속하기`를 클릭한다.
5. 프로필 이미지와 한 줄 소개 입력은 선택 정보이므로 필수 입력 정보는 아니다. 완료하였으면 `가입하기`를 누른다.
6. 맨 처음 `홈` 화면에는 공유 or 공유받은 음악이 없기에 아무런 목록이 뜨지 않는다.
7. nav바의 `친구` 탭에 들어가 지인의 아이디를 검색해 친구 신청을 보낸다. (아이디는 카카오 이메일의 @앞 부분이 아이디이다. 회원가입 시 고정으로 적혀있는 부분이다.)
8. 상대가 친구 신청을 수락 시, 휴대폰 상단의 알림바에 알림이 온다.
9. `공유` 탭에 들어가 음악을 검색한다. 
10. 음악을 선택 후 중앙의 `+`버튼을 눌러 바텀시트가 올라오는 걸 확인 후 공유할 친구를 선택한다. 
11. 친구 선택 후 코멘트 작성은 자유이며, 완료했다면 전송하기를 누른다. 
12. 공유가 성공적으로 되었다면 `홈`화면에 상단에 `나`로 표시되어있는 페이지에서 내가 공유한 음악이 보인다.
13. 이후 친구가 나에게 음악을 공유했다면 알림과 동시에 바탕화면의 위젯이 공유받은 음악의 앨범커버와 오른쪽 하단의 프로필이 해당 음악을 공유한 친구의 닉네임의 앞 자리로 변경된다.
14. 공유받은 음악의 경우 `홈`화면의 `나`버튼을 클릭하면 친구 닉네임이 드롭다운으로 나타나며 해당 닉네임을 클릭 시 친구가 공유한 음악을 볼 수 있다. (혹은 위젯이나 알림에서 바로 타고 들어가도 괜찮다.)
15. 앨범 커버를 클릭하면 노래가 재생되며, 5초 이상 재생 시 친구가 보낸 코멘트를 읽을 수 있다.
16. 음악이 마음에 든 경우 `좋아요` 버튼을 누를 수 있으며 오른쪽의 `재전송` 버튼을 통해 다른 친구에게도 음악을 공유할 수 있다. 
17. 회원가입 시 정보를 수정하고 싶은 경우, nav바의 `내 정보`에 들어가 `연필 아이콘`을 클릭해 프로필 이미지와 닉네임, 한줄소개, 라벨 선택을 수정할 수 있다.
18. `내 정보`에서 내가 공유한 음악을 확인할 수 있다.
19. `내 정보`에서 `탈퇴하기`를 클릭하면 회원정보가 삭제되며 맨 처음 가입 화면으로 돌아온다. 



### 시연 영상
- https://www.youtube.com/watch?v=1N-SCgzZkAY



### 개발자
> **Android**  
<p align="left">
  <a href="https://github.com/akuby21">
    <img src="https://github.com/akuby21.png" width="200" height="200" alt="akuby21"/>
  </a>
  <a href="https://github.com/anyooin">
    <img src="https://github.com/anyooin.png" width="200" height="200" alt="anyooin"/>
  </a>
  <a href="https://github.com/nJiyeon">
    <img src="https://github.com/nJiyeon.png" width="200" height="200" alt="nJiyeon"/>
  </a>
  <a href="https://github.com/YJY1220">
    <img src="https://github.com/YJY1220.png" width="200" height="200" alt="YJY1220"/>
  </a>
</p>

> **BE**  
<p align="left">
  <a href="https://github.com/BGMSound">
    <img src="https://github.com/BGMSound.png" width="200" height="200" alt="BGMSound"/>
  </a>
  <a href="https://github.com/Youngini">
    <img src="https://github.com/Youngini.png" width="200" height="200" alt="Youngini"/>
  </a>
  <a href="https://github.com/doyooon">
    <img src="https://github.com/doyooon.png" width="200" height="200" alt="doyooon"/>
  </a>
</p>
