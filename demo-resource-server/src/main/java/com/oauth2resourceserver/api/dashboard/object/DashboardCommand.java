package com.oauth2resourceserver.api.dashboard.object;

import com.oauth2resourceserver.base.command.object.BaseCommand;

import lombok.Getter;
import lombok.Setter;

public class DashboardCommand extends BaseCommand{
    public @Getter @Setter String command;
}