package org.anarres.dhcp.v6.service;

import com.google.common.annotations.Beta;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.anarres.dhcp.v6.Dhcp6Exception;
import org.anarres.dhcp.v6.io.Dhcp6RequestContext;
import org.anarres.dhcp.v6.messages.Dhcp6Message;

@Beta
public interface Dhcp6LeaseManager {

    // TODO add javadoc + rfc link

    // TODO split lease for solicit and request. What if no request comes ? do we free the adresses automatically, but when ?
    @Nonnull Dhcp6Message lease(final Dhcp6RequestContext requestContext, Dhcp6Message incomingMsg, Dhcp6Message reply)
        throws Dhcp6Exception;

    @Nonnull Dhcp6Message release(final Dhcp6RequestContext requestContext, Dhcp6Message options, Dhcp6Message reply)
        throws Dhcp6Exception;

    @Nonnull Dhcp6Message requestInformation(final Dhcp6RequestContext requestContext, Dhcp6Message incomingMsg, Dhcp6Message reply)
        throws Dhcp6Exception;

    /**
     * Lease manager needs to add a status code to the reply Success/NotOnLink. If there is not enough information to
     * confirm the request. Lease manager should return null.
     */
    @Nullable Dhcp6Message confirm(Dhcp6RequestContext requestContext, Dhcp6Message incomingMsg, Dhcp6Message reply)
        throws Dhcp6Exception;

    @Nonnull Dhcp6Message renew(Dhcp6RequestContext requestContext, Dhcp6Message incomingMsg, Dhcp6Message reply)
        throws Dhcp6Exception;

    @Nullable Dhcp6Message rebind(Dhcp6RequestContext requestContext, Dhcp6Message incomingMsg, Dhcp6Message reply)
        throws Dhcp6Exception;

    @Nonnull Dhcp6Message decline(Dhcp6RequestContext requestContext, Dhcp6Message incomingMsg, Dhcp6Message reply)
        throws Dhcp6Exception;

    /**
     * Handle unknown/undefined message. If a DHCPv6 message does not fall into any of the above methods, this one will be invoked.
     * The implementations of LeaseManager might support additional DHCPv6 message types.
     */
    @Nullable Dhcp6Message handle(Dhcp6RequestContext requestContext, Dhcp6Message incomingMsg, final byte msgType) throws Dhcp6Exception;

}
