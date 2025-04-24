FROM ubuntu

WORKDIR /work

ARG USERNAME

SHELL [ "/bin/bash", "-c" ]

# ホストユーザーをDevContainer上に作成
ENV GID=1000
ENV UID=1000
RUN groupadd -g $GID -o ${USERNAME} \
    && useradd -d /home/${USERNAME} -m -u $UID -g $GID -o -s /bin/bash $USERNAME

# sudoをパスワード無で実行できるように設定
RUN echo "$USERNAME ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers

# ソースコードを配置する作業ディレクトリを作成
RUN mkdir -m 777 /workspaces

USER ${USERNAME}