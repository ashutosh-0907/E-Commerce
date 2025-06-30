package org.gfg.NotificationService.utility;

public class EmailTemplate {

    public static String EMAIL_TEMPLATE = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Welcome to Your E-Commerce</title>\n" +
            "    <style>\n" +
            "        body {\n" +
            "            font-family: Arial, sans-serif;\n" +
            "            background-color: #f4f7fa;\n" +
            "            color: #333;\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "        }\n" +
            "        .container {\n" +
            "            width: 100%;\n" +
            "            max-width: 600px;\n" +
            "            margin: 0 auto;\n" +
            "            background-color: #ffffff;\n" +
            "            border-radius: 8px;\n" +
            "            overflow: hidden;\n" +
            "        }\n" +
            "        .header {\n" +
            "            background-color: #0044cc;\n" +
            "            padding: 20px;\n" +
            "            color: #ffffff;\n" +
            "            text-align: center;\n" +
            "        }\n" +
            "        .header h1 {\n" +
            "            margin: 0;\n" +
            "            font-size: 32px;\n" +
            "        }\n" +
            "        .body {\n" +
            "            padding: 20px;\n" +
            "            text-align: center;\n" +
            "        }\n" +
            "        .body h2 {\n" +
            "            font-size: 24px;\n" +
            "            margin-bottom: 20px;\n" +
            "        }\n" +
            "        .body p {\n" +
            "            font-size: 16px;\n" +
            "            line-height: 1.6;\n" +
            "        }\n" +
            "        .cta-button {\n" +
            "            display: inline-block;\n" +
            "            background-color: #28a745;\n" +
            "            color: #ffffff;\n" +
            "            padding: 12px 30px;\n" +
            "            text-decoration: none;\n" +
            "            font-size: 18px;\n" +
            "            border-radius: 5px;\n" +
            "            margin-top: 20px;\n" +
            "        }\n" +
            "        .footer {\n" +
            "            background-color: #f1f1f1;\n" +
            "            text-align: center;\n" +
            "            padding: 15px;\n" +
            "            font-size: 14px;\n" +
            "            color: #777;\n" +
            "        }\n" +
            "        .footer p {\n" +
            "            margin: 0;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"container\">\n" +
            "        <div class=\"header\">\n" +
            "            <h1>Welcome to JBDL E-Commerce</h1>\n" +
            "        </div>\n" +
            "        <div class=\"body\">\n" +
            "            <h2>Congratulations on Joining!</h2>\n" +
            "            <p>Hello ==name==,</p>\n" +
            "            <p>Welcome aboard! We’re excited to have you as a new member of our E-Commerce family. You now have the power to order, search, and see your favorite products effortlessly at any time, anywhere.</p>\n" +
            "            <p>To get started, click the button below and explore the amazing features that await you:</p>\n" +
            "            <a href=\"https://JBDL.com/start\" class=\"cta-button\">Get Started</a>\n" +
            "        </div>\n" +
            "        <div class=\"footer\">\n" +
            "            <p>If you have any questions or need support, feel free to reach out to us at support@yourapp.com.</p>\n" +
            "            <p>&copy; 2024 Your E-Commerce App. All rights reserved.</p>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>\n";
}