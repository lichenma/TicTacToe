package com.webservice.tictactoe.security;

import com.webservice.tictactoe.domain.Player;
import com.webservice.tictactoe.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PlayerRepository playerRepository;

    @Autowired
    public UserDetailsServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository=playerRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        checkNotNull(username);

        if(isEmpty(username)) {
            throw new UsernameNotFoundException("Username cannot be empty");
        }

        Player player=playerRepository.findOneByUserName(username);
        if (player==null){
            throw new UsernameNotFoundException("Player "+username+" doesn't exist");
        }
        return new ContextUser(player);
    }
}
