package com.example.advancedemailvalidator;

import org.junit.Test;
import org.junit.Assert;

import static org.hamcrest.CoreMatchers.is;

public class AdvancedEmailValidatorTest {

    @Test
    public void test_validEmails_trueReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("john@domain.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("john@domain.com.ph"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("john@g.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("john@localhost"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("john@localhost"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("a@domain.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("prettyandsimple@example.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("very.common@example.com"), is(true));
    }

    @Test
    public void test_simpleWrongEmails_falseReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate(null), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john.domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john@g(mai)l.com"), is(false));
    }

    @Test
    public void test_emailsWithAtSign_falseReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("adr@ian@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("adr@ian@lorem@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john@domain@com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("adr@ian@domain.com@ph"), is(false));
    }

    @Test
    public void test_emailsWithSpaces_trueReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("john@domain.com  "), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("   john@domain.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("   john@domain.com  "), is(true));
    }

    @Test
    public void test_emailsWithSpaces_falseReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("adri an@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("adri an@gma il.com"), is(false));
    }

    @Test
    public void test_emailsWithDash_trueReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("other.email-with-dash@example.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("example-indeed@strange-example.com"), is(true));
    }

    @Test
    public void test_emailsWithDash_falseReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("john@-domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john@domain.com-"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john@-domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("-john@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john-@domain.com"), is(false));
    }

    @Test
    public void test_emailsWithDots_trueReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("john.doe@domain.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("john.doe@domain.com.ph"), is(true));
    }

    @Test
    public void test_emailsWithDots_falseReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("john.doe@domain..com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john..doe@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate(".john.doe@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john.doe.@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john.doe@.domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john.doe@domain.com."), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john.lorem..doe@domain.com"), is(false));
    }

    @Test
    public void test_emailsWithIpAddress_trueReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("john@[192.168.1.1]"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("john@[255.255.255.0]"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("john@[175.158.201.59]"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("john@[684D:1111:222:3333:4444:5555:6:77]"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("test@[IPv6:2018:db8::1]"), is(true));
    }

    @Test
    public void test_emailsWithIpAddress_falseReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("john@[domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john@domain.com]"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john@[]"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john@[test]"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john@192.168.1.1"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john@175.158.201.59"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("john@684D:1111:222:3333:4444:5555:6:77"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("test@IPv6:2018:db8::1"), is(true));
    }

    @Test
    public void test_emailWithSpecialCharacters_trueReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("disposable.style.email.with+symbol@example.com"), is(true));
    }

    @Test
    public void test_emailsWithSpecialCharactersPurely_trueReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("#!$%&'*+-/=?^_`{}|~@example.org"), is(true));
    }

    @Test
    public void test_emailWithSpecialCharacters_falseReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("semicolon;@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("a\"b(c)d,e:f;gi[j\\k]l@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("()<>[]:,;!#$%&'-/=?^_`{}|~.a\"@example.org"), is(false));
    }

    @Test
    public void test_emailsWithQuotes_trueReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("\"()<>[]:,;!#$%&'-/=?^_`{}|~.a\"@example.org"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("\"xyz.test.@.test.com\"@domain.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("\"()<>[]:,;@\"!#$%&’-/=?^_`{}|~.a\"@domain.org"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("abc.\"test\".email@domain.com"), is(true));
    }

    @Test
    public void test_emailsWithQuotes_falseReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("()<>[]:,;!#$%&'-/=?^_`{}|~.a\"@example.org"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("abc\\is\"not\\valid@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("abc\"test\"email@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("abc.\"test\"email@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("abc\"test\".email@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("abc.\"test\",email@domain.com"), is(false));
    }

    @Test
    public void test_validEmailsDocument_trueReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("test@domain.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("lastname@domain.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("test.email.with+symbol@domain.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("id-with-dash@domain.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("a@domain.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("\"xyz.test.@.test.com\"@domain.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("\"abc.(),:;<>[]\\\".EMAIL.\\\"email@\\\\\"email\\\".test\"@strange.domain.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("example-abc@abc-domain.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("admin@mailserver1"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("#!$%&'*+-/=?^_`{}|~@example.org"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("\"()<>[]:,;@\"!#$%&’-/=?^_`{}|~.a\"@domain.org"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("example@localhost"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("example@s.solutions"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("test@com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("test@localserver"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("(comment)xyz@example.com"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("john@[684D:1111:222:3333:4444:5555:6:77]"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("test@[IPv6:2018:db8::1]"), is(true));
        Assert.assertThat(AdvancedEmailValidator.validate("abc.\"test\".email@domain.com"), is(true));
    }

    @Test
    public void test_invalidEmailsDocument_falseReturned() {
        Assert.assertThat(AdvancedEmailValidator.validate("example.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("A@b@c@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("a\"b(c)d,e:f;gi[j\\k]l@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("abc\"test\"email@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("abcis\"not\\valid@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("abc\\is\"not\\valid@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("abc\"isnot\\valid@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("abc\\\"isnot\\valid@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate(".test@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate("..test@domain.com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate(".test@domain..com"), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate(".test@domain.com.."), is(false));
        Assert.assertThat(AdvancedEmailValidator.validate(".test@domain.com."), is(false));
    }
}