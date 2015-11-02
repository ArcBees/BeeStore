/*
 * Copyright 2015 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.beestore.client.resources;

import com.google.gwt.i18n.client.Messages;

public interface SocialSharingUrls extends Messages {
    @DefaultMessage("http://www.facebook.com/sharer.php?u={0}")
    String facebook(String url);

    @DefaultMessage("https://pinterest.com/pin/create/bookmarklet/?url={0}")
    String pinterest(String url);

    @DefaultMessage("https://plus.google.com/share?url={0}")
    String googlePlus(String url);

    @DefaultMessage("https://twitter.com/share?url={0}")
    String twitter(String url);

    @DefaultMessage("https://www.tumblr.com/widgets/share/tool?canonicalUrl={0}")
    String tumblr(String url);

    @DefaultMessage("mailto:?subject=Beestore%20product&body={0}")
    String email(String url);
}
