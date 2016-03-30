.class public Lcom/apkfuns/andfixdemo/SecondActivity_CF;
.super Landroid/support/v7/app/AppCompatActivity;
.source "SecondActivity.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 11
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    return-void
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 1
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;
    .annotation runtime Lcom/alipay/euler/andfix/annotation/MethodReplace;
        clazz = "com.apkfuns.andfixdemo.SecondActivity"
        method = "onCreate"
    .end annotation

    .prologue
    .line 15
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 16
    const v0, 0x7f040019

    invoke-virtual {p0, v0}, Lcom/apkfuns/andfixdemo/SecondActivity_CF;->setContentView(I)V

    .line 17
    const-string v0, "\u8fd9\u662f\u7b2c\u4e8c\u4e2a\u754c\u9762"

    invoke-virtual {p0, v0}, Lcom/apkfuns/andfixdemo/SecondActivity_CF;->setTitle(Ljava/lang/CharSequence;)V

    .line 24
    return-void
.end method
