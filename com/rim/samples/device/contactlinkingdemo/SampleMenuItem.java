/**
 * SampleMenuItem.java
 *
 * Copyright � 1998-2011 Research In Motion Limited
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Note: For the sake of simplicity, this sample application may not leverage
 * resource bundles and resource strings.  However, it is STRONGLY recommended
 * that application developers make use of the localization features available
 * within the BlackBerry development platform to ensure a seamless application
 * experience across a variety of languages and geographies.  For more information
 * on localizing your application, please refer to the BlackBerry Java Development
 * Environment Development Guide associated with this release.
 */

package com.rim.samples.device.contactlinkingdemo;

import net.rim.blackberry.api.menuitem.ApplicationMenuItem;
import net.rim.blackberry.api.pdap.BlackBerryContact;
import net.rim.blackberry.api.pdap.contactlinking.LinkedContactUtilities;
import net.rim.device.api.ui.image.Image;

/**
 * An ApplicationMenuItem that will be displayed in the Address Book application
 */
public class SampleMenuItem extends ApplicationMenuItem {
    private final long _applicationID;

    /**
     * Creates a new SampleMenuItem object
     * 
     * @param applicationID
     *            The id of the application this ApplicationMenuItem is for
     * @param icon
     *            Icon for the menu item
     */
    public SampleMenuItem(final long applicationID, final Image icon) {
        super(0x230010, icon);
        _applicationID = applicationID;
    }

    /**
     * Fires an event for the contact passed into this method
     * 
     * @see ApplicationMenuItem#run(Object)
     */
    public Object run(final Object context) {
        if (context instanceof BlackBerryContact) {
            final BlackBerryContact contact = (BlackBerryContact) context;

            final String userId =
                    LinkedContactUtilities.getContactIdFromLinkedContact(
                            contact, _applicationID);
            if (userId != null) {
                final SampleContact user =
                        ContactListScreen.getUserForID(userId);
                EventScreen.fireEvent(user);
            }
        }
        return null;
    }

    /**
     * @see Object#toString()
     */
    public String toString() {
        return "Sample Menu Item";
    }
}
