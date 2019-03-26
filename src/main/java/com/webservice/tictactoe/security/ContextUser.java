package com.webservice.tictactoe.security;

import com.webservice.tictactoe.domain.Player;
import com.google.common.collect.ImmutableSet;
import org.aspectj.apache.bcel.util.Play;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class ContextUser extends org.springframework.security.core.userdetails.User{

    private final Player player;

    public ContextUser(Player player) {
        super(player.getUserName(),
                player.getPassword(),
                true,
                true,
                true,
                true,
                ImmutableSet.of(new SimpleGrantedAuthority("create")));
        this.player=player;
    }

    public Player getPlayer() {
        return player;
    }
}
