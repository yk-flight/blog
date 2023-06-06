<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>【雅康博客】系统邮件</title>
    <style>
        .email-container {
            padding: 20px;
            width: 100%;
            display: flex;
            align-items: center;
            flex-direction: column;
        }
        .card {
            padding: 30px 20px;
            width: 500px;
            border-radius: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
        }
        .image-container {
            width: 100%;
            text-align: center;
        }
        .content {
            width: 100%;
            margin: 20px 0;
        }
        .header {
            font-weight: 500;
            font-size: 18px;
            margin-bottom: 8px;
        }
        .code {
            font-size: 25px;
            font-weight: 600;
            margin: 20px 0;
            text-align: center;
        }
        .footer {
            margin: 30px 0;
            color: #8a8c90;
            text-indent:2em;
        }
    </style>
</head>
<body>
<div class="email-container">
    <div class="card">
        <div class="image-container">
            <img height="auto" width="200" src="https://www.drageasy.com/1ce6cbe702a1f66ee4f00e3bbc9b8e28.png?imageslim">
            <!-- <h2>雅康的个人博客</h2> -->
        </div>
        <hr />
        <div class="content">
            <!-- 问候语 -->
            <span class="header">尊敬的${nickname}：</span>
            <!-- 验证码主体内容 -->
            <p style="text-indent: 2em;">
                您正在执行敏感操作，您的验证码为：
            <div class="code">
                ${verifyCode}
            </div>
            <span style="display:block;text-indent:2em;color: rgb(183, 43, 43);">
              本条验证码有效时间为 5 分钟。
          </span>
            <p style="text-indent:2em;">
                转发给他人可能导致账号被盗，请勿泄漏，谨防被骗。
            </p>
            </p>
        </div>
        <div class="footer">
            此邮件由系统自动发送，请勿直接回复，如果不是您的操作，请直接无视此邮件。<br />
            <p style="text-indent: 2em;">
                如果有问题可以在 ${website} 来联系我们。</p>
        </div>
    </div>
</div>

</body>
</html>