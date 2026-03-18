import { useState } from 'react';
import { useAppContext } from '../../context/AppContext';
import './Admin.css';

const Admin = () => {
  const { data, updateSoftwareLinks, updateGameLinks, updateNavigationLinks } = useAppContext();
  
  // 状态管理
  const [softwareLinks, setSoftwareLinks] = useState(data.softwareLinks);
  const [gameLinks, setGameLinks] = useState(data.gameLinks);
  const [navigationLinks, setNavigationLinks] = useState(data.navigationLinks);

  // 添加软件链接
  const addSoftwareLink = () => {
    const newLink = {
      id: Date.now(),
      name: '新软件',
      url: '#',
      description: '软件描述'
    };
    setSoftwareLinks([...softwareLinks, newLink]);
  };

  // 删除软件链接
  const deleteSoftwareLink = (id) => {
    setSoftwareLinks(softwareLinks.filter(link => link.id !== id));
  };

  // 更新软件链接
  const updateSoftwareLink = (id, field, value) => {
    setSoftwareLinks(softwareLinks.map(link => 
      link.id === id ? { ...link, [field]: value } : link
    ));
  };

  // 添加游戏链接
  const addGameLink = () => {
    const newLink = {
      id: Date.now(),
      name: '新游戏',
      url: '#',
      description: '游戏描述'
    };
    setGameLinks([...gameLinks, newLink]);
  };

  // 删除游戏链接
  const deleteGameLink = (id) => {
    setGameLinks(gameLinks.filter(link => link.id !== id));
  };

  // 更新游戏链接
  const updateGameLink = (id, field, value) => {
    setGameLinks(gameLinks.map(link => 
      link.id === id ? { ...link, [field]: value } : link
    ));
  };

  // 添加导航链接
  const addNavigationLink = () => {
    const newLink = {
      id: Date.now(),
      name: '新导航',
      url: '#'
    };
    setNavigationLinks([...navigationLinks, newLink]);
  };

  // 删除导航链接
  const deleteNavigationLink = (id) => {
    setNavigationLinks(navigationLinks.filter(link => link.id !== id));
  };

  // 更新导航链接
  const updateNavigationLink = (id, field, value) => {
    setNavigationLinks(navigationLinks.map(link => 
      link.id === id ? { ...link, [field]: value } : link
    ));
  };

  // 保存所有更改
  const saveChanges = () => {
    updateSoftwareLinks(softwareLinks);
    updateGameLinks(gameLinks);
    updateNavigationLinks(navigationLinks);
    alert('更改已保存！');
  };

  return (
    <div className="admin-container">
      <h1>后台管理</h1>
      
      {/* 导航链接管理 */}
      <section className="admin-section">
        <h2>导航链接管理</h2>
        <button onClick={addNavigationLink} className="add-btn">添加导航链接</button>
        <div className="links-list">
          {navigationLinks.map(link => (
            <div key={link.id} className="link-item">
              <input 
                type="text" 
                value={link.name} 
                onChange={(e) => updateNavigationLink(link.id, 'name', e.target.value)}
                placeholder="导航名称"
              />
              <input 
                type="text" 
                value={link.url} 
                onChange={(e) => updateNavigationLink(link.id, 'url', e.target.value)}
                placeholder="导航链接"
              />
              <button onClick={() => deleteNavigationLink(link.id)} className="delete-btn">删除</button>
            </div>
          ))}
        </div>
      </section>

      {/* 软件链接管理 */}
      <section className="admin-section">
        <h2>软件链接管理</h2>
        <button onClick={addSoftwareLink} className="add-btn">添加软件链接</button>
        <div className="links-list">
          {softwareLinks.map(link => (
            <div key={link.id} className="link-item">
              <input 
                type="text" 
                value={link.name} 
                onChange={(e) => updateSoftwareLink(link.id, 'name', e.target.value)}
                placeholder="软件名称"
              />
              <input 
                type="text" 
                value={link.url} 
                onChange={(e) => updateSoftwareLink(link.id, 'url', e.target.value)}
                placeholder="软件链接"
              />
              <input 
                type="text" 
                value={link.description} 
                onChange={(e) => updateSoftwareLink(link.id, 'description', e.target.value)}
                placeholder="软件描述"
              />
              <button onClick={() => deleteSoftwareLink(link.id)} className="delete-btn">删除</button>
            </div>
          ))}
        </div>
      </section>

      {/* 游戏链接管理 */}
      <section className="admin-section">
        <h2>游戏链接管理</h2>
        <button onClick={addGameLink} className="add-btn">添加游戏链接</button>
        <div className="links-list">
          {gameLinks.map(link => (
            <div key={link.id} className="link-item">
              <input 
                type="text" 
                value={link.name} 
                onChange={(e) => updateGameLink(link.id, 'name', e.target.value)}
                placeholder="游戏名称"
              />
              <input 
                type="text" 
                value={link.url} 
                onChange={(e) => updateGameLink(link.id, 'url', e.target.value)}
                placeholder="游戏链接"
              />
              <input 
                type="text" 
                value={link.description} 
                onChange={(e) => updateGameLink(link.id, 'description', e.target.value)}
                placeholder="游戏描述"
              />
              <button onClick={() => deleteGameLink(link.id)} className="delete-btn">删除</button>
            </div>
          ))}
        </div>
      </section>

      {/* 保存按钮 */}
      <div className="save-section">
        <button onClick={saveChanges} className="save-btn">保存更改</button>
        <a href="/" className="back-btn">返回首页</a>
      </div>
    </div>
  );
};

export default Admin;
