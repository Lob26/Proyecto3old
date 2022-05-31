package com.uniandes.lithub.controller;

public class HubControllerBuilder {
    private SavedProjectInfo spi;

    public HubControllerBuilder setSpi(SavedProjectInfo spi) {
        this.spi = spi;
        return this;
    }

    public HubController createHubController() {
        return new HubController(spi);
    }
}