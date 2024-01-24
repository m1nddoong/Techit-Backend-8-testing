package com.example.contents;

import com.example.contents.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// UserRepository 의 단위 테스트를 위한 테스트
@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    // 사용자를 추가하는 테스트
    @Test
    @DisplayName("새로운 User 추가")
    public void testCreateUser() {
        // 테스트의 가독성을 높이는 패턴
        // given - 테스트를 진행하기 위한 기본 조건을 만들어주는 부분
        // 내가 만들고자 하는 User 엔티티가 있는 상황에서
        String username = "m1nddoong";
        User user = new User(username, null,  null, null); // 일단은 이름만 넣어두기

        // when - 실제러 테스트를 진행하는 부분
        // userRepository.save(user)를 진행한다.
        User result = userRepository.save(user); // save 한 결과를 받아온다

        // then - 내가 기대한대로 동작했는지 검증
        // userRepository.save()의 결과의 username에 본래 User의 username과 일치하는지
        // 즉, username 은 바뀌지 않았고, id는 null 아 아니게되었다를 입증하게 되면 userReopsitory 정상 동작하는 것이다.
        assertEquals(username, result.getUsername());

        // userRepository.save()의 결과의 id가 null이 아닌지
        assertNotNull(result.getId());
        // assertNotNull(result.getEmail()); // email 이 null 이기 떄문에 test 실패
    }

    // 사용자를 추가하는데 실패하는 테스트
    // 두명의 사용자는 username이 겹치면 안되니까
    // 하나의 username을 가진 사람이 있다고 가정하고
    // 같은 usernamae을 사용해서 User를 생성할때는 실패해야한다.
    @Test
    @DisplayName("새로운 User 추가 실패 (중복 username)")
    public void testCreateUserFail() { // 실패를 해야 정상 동작인 것임
        // given - 어떤 특정 username을 가진 User가 이미 저장된 상황에서
        String username = "m1nddoong";
        User userGiven = new User(username, null, null, null);
        userRepository.save(userGiven); // 사용자가 이미 존재

        // when - 동일한 useranme을 가진 user를 저장하려고 하면
        // 이미 존재하는 사용자와 같은 이름으로 새로운 newUser를 저장하려고 함
        User newUser = new User(username, null, null, null);

        // then - 실패한다.
        // ()-> userRepository.save(newUser) 를 하려고 했는데 실패했다 -> 성공
        assertThrows(Exception.class, ()-> userRepository.save(newUser));
    }

    // 사용자를 조회하는 테스트
    @Test
    @DisplayName("username으로 존재하는 사용자 조회")
    public void testReadUser() {
        // given (준비과정)
        // 내가 읽고자 하는 특정 username의 User가 데이터베이스에 저장된 이후의 상황에서
        String username = "m1nddoong";
        User userGiven = new User(username, null, null, null);
        userRepository.save(userGiven); // 이렇게 하면 사용자는 이미 저장되어있는 상태라고 볼 수 있다.

        // when (내가 테스트하고 싶은 기능)
        // 해당하는 username을 가지고 userRepository.findByUsername(username); 의 결과를 받아오면
        Optional<User> optionalUser = userRepository.findByUsername(username);

        // then (검증 결과)
        // 돌아온 결과 Optional.isPresent() == true 이고, (assertTrue)
        assertTrue(optionalUser.isPresent());

        // 돌아온 결과 Optional.get().getUsername == username 이다.
        assertEquals(username, optionalUser.get().getUsername());
    }




    // 존재하지 않는 username을 가지고 조회하면 Optional.empty()가 반환된다.

    // 이미 존재하는 username을 검색해서 존재하는지 확인한다.

    // id를 가지고 User를 삭제한다.
}
