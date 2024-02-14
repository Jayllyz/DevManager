package domain.projects.attributes;

import org.junit.jupiter.api.Test;
import shared.exceptions.InvalidAttributeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DescriptionTest {
    @Test
    void shouldThrowExceptionWhenDescriptionIsNotSet() {
        InvalidAttributeException exception = assertThrows(InvalidAttributeException.class,() -> {
            Description description = new Description(null);
        });

        String expectedMessage = "Project description is not defined";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsTooLong() {
        InvalidAttributeException exception = assertThrows(InvalidAttributeException.class, () -> {
            Description description = new Description("""
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et sagittis sem. Cras ut mi at nibh blandit semper. Fusce volutpat venenatis ipsum, aliquam tempor mi. Etiam vestibulum metus in nisi consequat, ut aliquet augue condimentum. Nam molestie nunc at sem dapibus, a bibendum dolor vestibulum. Curabitur id neque eu nibh venenatis elementum. Donec a urna convallis, sagittis diam id, pellentesque dolor. Duis quis nibh tempor, dignissim dolor vitae, facilisis orci. Nullam malesuada felis lectus, quis tincidunt libero posuere non. Nam viverra finibus orci, eget gravida quam congue eleifend. Etiam vel eleifend felis. Ut hendrerit lobortis commodo. Ut tincidunt vulputate varius. Integer sed felis tortor. Nunc malesuada sit amet nulla at malesuada. Suspendisse condimentum libero in dui sagittis volutpat.
                                        
                    Praesent hendrerit eu mi non feugiat. Ut tempor rhoncus est. Quisque quis molestie augue. Aenean sed enim sit amet diam faucibus pellentesque a ut odio. Sed maximus neque pharetra nisi molestie, ac finibus dolor scelerisque. Cras et vestibulum massa, ut accumsan nunc. Proin nec felis at leo euismod posuere. Maecenas nibh erat, porttitor vitae semper eu, aliquet eu lacus. Nulla volutpat erat sed massa aliquet, quis rutrum nisi rutrum. Phasellus vel facilisis eros. Integer faucibus aliquam tortor id lacinia. Suspendisse at odio a eros convallis semper. Donec pulvinar suscipit sem. Pellentesque ut nibh cursus, bibendum erat nec, mattis purus. Vivamus sit amet viverra enim, a blandit elit.
                                        
                    Nunc vel ante nec felis volutpat elementum id id leo. Maecenas convallis, leo ultrices hendrerit tempus, enim eros blandit leo, rutrum feugiat quam orci sit amet libero. Phasellus faucibus nunc a odio dapibus feugiat. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Sed tempus facilisis nibh, et pulvinar tortor facilisis ut. Aliquam convallis euismod nibh. Aliquam suscipit a libero ac suscipit.
                                        
                    Curabitur eleifend neque et magna porttitor tempus vitae vel dui. Maecenas eget vulputate velit. Donec feugiat ante erat, non pretium felis iaculis ut. Aenean eros dui, fermentum et consequat eu, ultricies sit amet metus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque id nisl id massa pretium semper. Quisque sed congue arcu. Aliquam felis urna, tempor vitae sollicitudin vitae, condimentum eget tellus. Integer ut tortor vestibulum, malesuada ex vitae, ornare quam. Proin sodales porta ultricies.
                                        
                    Sed in pretium odio, quis gravida dui. Aliquam nec maximus enim. In a facilisis lectus, ut auctor eros. Fusce orci nisi, eleifend quis turpis eget, sagittis consectetur nisl. Suspendisse sem turpis, semper eget tempor non, congue quis mauris. Mauris vitae interdum magna, in gravida eros. Vestibulum bibendum et velit eu finibus. Duis ac viverra turpis. Sed efficitur vitae diam et finibus. In posuere quis neque et pretium. Pellentesque egestas id diam ac commodo. Proin non neque a eros laoreet elementum. Duis et sapien leo. Pellentesque ut nunc turpis.
                    """);
        });

        String expectedMessage = "Project description can't be more than 500 characters";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
