/*
 * Copyright (c) 2016 Sergey Glotov.
 *
 *
 * This file is part of ARC70.
 *
 * ARC70 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ARC70 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ARC70.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.sergeich.autoradioclub.app;

import com.sergeich.autoradioclub.messages.MessagesComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ArcModule.class)
public interface ArcComponent {

    MessagesComponent messagesComponent();

}

