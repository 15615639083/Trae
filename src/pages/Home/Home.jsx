import { useAppContext } from '../../context/AppContext';
import '../../App.css';

const Home = () => {
  const { data } = useAppContext();

  return (
    <div className="app">
      {/* 导航栏 */}
      <nav className="navbar">
        <ul>
          {data.navigationLinks.map(link => (
            <li key={link.id}>
              <a href={link.url}>{link.name}</a>
            </li>
          ))}
        </ul>
      </nav>

      {/* 主内容 */}
      <main className="main">
        <div className="content">
          <h1>HELLO!</h1>
          <h2>欢迎</h2>
          <p>来到纸飞|</p>
          <p className="description">专注于软件安装以及资源分享服务，致力于为用户提供一个安全、可靠、免费的资源下载环境，</p>
          <div className="buttons">
            {data.softwareLinks.map(link => (
              <a key={link.id} href={link.url} className="btn">{link.name}</a>
            ))}
            {data.gameLinks.map(link => (
              <a key={link.id} href={link.url} className="btn">{link.name}</a>
            ))}
          </div>
        </div>
      </main>

      {/* 页脚 */}
      <footer className="footer">
        <p>©2025 纸飞资 版权所有  鲁ICP备2024120163号 </p>
        <p>鲁公网安备37078302000915号</p>
      </footer>
    </div>
  );
};

export default Home;
